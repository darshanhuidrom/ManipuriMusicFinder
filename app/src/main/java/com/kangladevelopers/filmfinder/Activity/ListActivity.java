package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.RvListAdapter;
import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.FileFetcher;
import com.kangladevelopers.filmfinder.utils.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListActivity extends BaseActivity  implements AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private ListView lvListAll;
    private SimpleAdapter adapter;
    private RvListAdapter rvListAdapter;
    private RecyclerView recyclerView;
    private boolean is_from_normal=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(is_from_normal){
            setContentView(R.layout.activity_list1);
        }
        else {
            setContentView(R.layout.activity_list);
        }
     //

        mapWithXml();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeData();
    }


    private void initializeData() {
        String name = getIntent().getStringExtra("name");
        String[] dataList=null;
        switch (name) {
            case "Singer":
                dataList = FileFetcher.getSingerList();
                break;
            case "Composer":
                dataList=FileFetcher.getComposer();
                break;
            case "Director":
                dataList=FileFetcher.getDirectorList();
                break;
            case "Actor":
                dataList=FileFetcher.getActorList();
                break;
            case "Lyric":
                break;
            default:
                Toast.makeText(getApplicationContext(), "Default", Toast.LENGTH_SHORT).show();


        }

        getSupportActionBar().setTitle(name);

        if(dataList!=null){
            List<String>  list = Arrays.asList(dataList);
            Collections.sort(list);

            adapter = new SimpleAdapter(getApplicationContext(),list );
            if(is_from_normal){
                rvListAdapter = new RvListAdapter(getApplicationContext(),list, new RvListAdapter.RvAdapterClickListener() {
                    @Override
                    public void onItemClick(int i, View v) {
                        String name = rvListAdapter.getDataAt(i);
                        if(name.contains(":")){
                            int pos =name.indexOf(":");
                            name=name.substring(0,pos);
                        }
                        Intent intent= new Intent(ListActivity.this,BioDataActivity.class);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }
                });
                recyclerView.setLayoutManager(new GridLayoutManager(ListActivity.this, 3));
                recyclerView.setAdapter(rvListAdapter);
            }
            else {
                lvListAll.setAdapter(adapter);
                lvListAll.setOnItemClickListener(this);
            }



        }


    }

    private void mapWithXml() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(is_from_normal){
            recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        }
        else {
            lvListAll = (ListView) findViewById(R.id.lv_list);
        }





    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = parent.getItemAtPosition(position).toString();
        if(name.contains(":")){
            int pos =name.indexOf(":");
            name=name.substring(0,pos);
        }
        Intent intent= new Intent(this,BioDataActivity.class);
        intent.putExtra("name",name);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener{

    private Toolbar toolbar;
    private ListView lvListAll;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mapWithXml();
        setSupportActionBar(toolbar);

        initializeData();
    }


    private void initializeData() {
        String name = getIntent().getStringExtra("name");
        String[] dataList=null;
        switch (name) {
            case "Singers":
                dataList = StringUtility.getSingerList();
                break;
            case "Composers":
                dataList=StringUtility.getComposer();
                break;
            case "Directors":
                dataList=StringUtility.getDirectorList();
                break;
            case "Actors":
                dataList=StringUtility.getActorList();
                break;
            case "Lyrics":
                break;
            default:
                Toast.makeText(getApplicationContext(), "Default", Toast.LENGTH_SHORT).show();
                getDelegate().getSupportActionBar().setTitle(name);

        }

        if(dataList!=null){
            adapter = new SimpleAdapter(getApplicationContext(), Arrays.asList(dataList));
            lvListAll.setAdapter(adapter);
            lvListAll.setOnItemClickListener(this);
        }


    }

    private void mapWithXml() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvListAll = (ListView) findViewById(R.id.lv_list);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this,BioDataActivity.class));

    }
}

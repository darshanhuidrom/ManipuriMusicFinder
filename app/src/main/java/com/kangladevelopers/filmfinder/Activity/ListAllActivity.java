package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.developers.ui.DeveloperActivity;

import java.util.ArrayList;

public class ListAllActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView lvListAll;
    private SimpleAdapter adapter;
    private boolean IS_FROM_DEVELOPER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall);
        mapWithXml();
        setSupportActionBar(toolbar);
        getDelegate().getSupportActionBar().setTitle("List All");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeData();
        IS_FROM_DEVELOPER= getIntent().getBooleanExtra("IS_FROM_DEVELOPER",false);
    }

    private void initializeData() {
        ArrayList<String> listName= new ArrayList<>();
        listName.add("Singer");
        listName.add("Composer");
        listName.add("Director");
        listName.add("Actor");
        listName.add("Lyric");
        adapter = new SimpleAdapter(getApplicationContext(),listName);
        lvListAll.setAdapter(adapter);
        lvListAll.setOnItemClickListener(this);

    }

    private void mapWithXml() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lvListAll= (ListView) findViewById(R.id.lv_list_all);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(IS_FROM_DEVELOPER){
            Intent intent = new Intent(this,DeveloperActivity.class);
            intent.putExtra("name",adapter.getData().get(position).toLowerCase());
            startActivity(intent);
            return;
        }
        Toast.makeText(getApplicationContext(),adapter.getData().get(position),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ListActivity.class);
        intent.putExtra("name",adapter.getData().get(position));

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

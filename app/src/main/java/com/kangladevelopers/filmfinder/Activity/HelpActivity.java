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

import com.kangladevelopers.filmfinder.Adapter.HelpAdapter;
import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.R;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    private HelpAdapter adapter;
    private Toolbar toolbar;
    private ListView lvHelp;
    private List<String> fields;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        lvHelp= (ListView) findViewById(R.id.lv_help);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createField();
        lvHelp.setOnItemClickListener(this);
    }

    private void createField(){
        fields = new ArrayList<>();
        fields.add("About");
        fields.add("FAQ");
        fields.add("Terms and Privacy");
        fields.add("Contact us");
        adapter = new HelpAdapter(getApplicationContext(),fields);
        lvHelp.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String field= (String) parent.getItemAtPosition(position);
        switch (field){
            case "About":
                break;
            case "FAQ":
                break;
            case "Terms and Privacy":
                break;
            case "Contact us":
                break;

        }
        Toast.makeText(getApplicationContext(),field,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HelpActivity.this,HelpDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

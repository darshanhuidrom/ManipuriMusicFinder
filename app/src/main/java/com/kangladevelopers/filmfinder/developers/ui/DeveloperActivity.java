package com.kangladevelopers.filmfinder.developers.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.Network.HTTP;
import com.kangladevelopers.filmfinder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeveloperActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SimpleAdapter adapter;
    private ListView lv;
    private TextView tv_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        mapWithXml();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Developers");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeData();
    }

    private void initializeData() {
        new AsyncTask<Void,Void,List<String>>(){

            @Override
            protected List<String> doInBackground(Void... params) {

                String url ="http://192.168.1.3:8080/move_finder/webapi/dev/getList/singer";
                String data = HTTP.sendGET(url);
                List<String> list = convertToList(data);
                return list;
            }

            @Override
            protected void onPostExecute(List<String> list) {
                if (list!=null){
                    adapter = new SimpleAdapter(DeveloperActivity.this,list);
                    lv.setAdapter(adapter);
                    tv_count.setText("Total count:  "+list.size());
                }
            }
        }.execute();
    }

    private void mapWithXml() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv= (ListView) findViewById(R.id.lv);
        tv_count = (TextView) findViewById(R.id.tv_count);

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



    public List<String> convertToList(String data){

        data = data.substring(1,data.length()-1);
        String[] strArray = data.split(",");
        return Arrays.asList(strArray);

    }
}

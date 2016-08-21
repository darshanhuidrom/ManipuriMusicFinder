package com.kangladevelopers.filmfinder.developers.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.Adapter.SimpleAdapter;
import com.kangladevelopers.filmfinder.Network.HTTP;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeveloperActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private SimpleAdapter adapter;
    private ListView lv;
    private TextView tv_count;
    ArrayList<String> dataCollectList = new ArrayList<>();
    private String firstName, secondName, dataType;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        mapWithXml();
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataType = getIntent().getStringExtra("name");
        getSupportActionBar().setTitle(dataType);
        initializeData();

    }

    private void initializeData() {
        new AsyncTask<Void, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... params) {

                String url = Constants.BASE_URL + "dev/getList/" + dataType;
                String data = HTTP.sendGET(url);
                List<String> list = convertToList(data);
                return list;
            }

            @Override
            protected void onPostExecute(List<String> list) {
                if (list != null) {
                    adapter = new SimpleAdapter(DeveloperActivity.this, list);
                    lv.setAdapter(adapter);
                    tv_count.setText("Total count:  " + list.size());
                }
            }
        }.execute();
    }


    private void mapWithXml() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lv = (ListView) findViewById(R.id.lv);
        tv_count = (TextView) findViewById(R.id.tv_count);
        lv.setOnItemClickListener(this);

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


    public List<String> convertToList(String data) {

        data = data.substring(1, data.length() - 1);
        String[] strArray = data.split(",");
        //   ArrayList<Name> listss = convertToNameList(strArray);
        return Arrays.asList(strArray);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        String data = parent.getItemAtPosition(position).toString();
        view.setBackgroundColor(Color.parseColor("#acdcaf"));//2e2d2d
        adapter.viewMap.put(data, "#acdcaf");


        if (firstName == null) {
            firstName = data;
        } else {
            secondName = data;
            Intent intent = new Intent(this, Correction.class);
            intent.putExtra("firstName", firstName);
            intent.putExtra("secondName", secondName);
            intent.putExtra("dataType", dataType);
            startActivity(intent);
            adapter.viewMap.put(firstName, "#2e2d2d");
            adapter.viewMap.put(secondName, "#2e2d2d");
            adapter.notifyDataSetChanged();
            firstName = null;
            secondName = null;
        }

    }


}

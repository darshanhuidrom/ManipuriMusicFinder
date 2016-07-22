package com.kangladevelopers.filmfinder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditMusicDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<String> dataList;
    private LinearLayout llParent;
    private LinearLayout llCastParent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataList=getIntent().getStringArrayListExtra("data");
        setContentView(R.layout.activity_edit_music_details);
        mapWithXml();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView(dataList);

    }

    private void mapWithXml() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        llParent= (LinearLayout) findViewById(R.id.ll_parent);
        llCastParent= (LinearLayout) findViewById(R.id.ll_parent2);
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

    private void addView(ArrayList<String> dataList){

        for (int i=0;i<dataList.size();i++){
            int pos = dataList.get(i).indexOf("#");
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.edit_music_details_block,null);
            TextView tv = (TextView) view.findViewById(R.id.tv_field);
            EditText et = (EditText) view.findViewById(R.id.et_field_value);
            tv.setText(dataList.get(i).substring(0,pos)+":");
            et.setText(dataList.get(i).substring(pos+1));
            llParent.addView(view);
        }

    }

    private void addCastView(String data){

        String[] arrayData= data.split(",");
        List<String> dataList= Arrays.asList(arrayData);
        for (int i=0;i<dataList.size();i++){
            int pos = dataList.get(i).indexOf("#");
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.edit_music_details_block,null);
            TextView tv = (TextView) view.findViewById(R.id.tv_field);
            EditText et = (EditText) view.findViewById(R.id.et_field_value);
            tv.setText(dataList.get(i).substring(0,pos)+":");
            et.setText(dataList.get(i).substring(pos+1));
            llParent.addView(view);
        }

    }


    public void onDeleteClick(View view){
        int id = view.getId();
       /* switch (id){
            case R.id.iv_delete_actorrrr:
                break;
            case R.id.iv_actor
        }*/
        Toast.makeText(getApplicationContext(),"onDeleteClick",Toast.LENGTH_SHORT).show();
    }

}

package com.kangladevelopers.filmfinder.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class BioDataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout llParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biodata);
        mapWithXml();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
    }

    private void mapWithXml() {
        llParent = (LinearLayout) findViewById(R.id.ll_parent);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        addView(new ArrayList<String>());

    }
    public void addView(List<String> list){
        ArrayList<String> strings =new ArrayList<>();
        strings.add("Mensinba");
        strings.add("Choirabi");
        strings.add("Sak Udribi");
        strings.add("Segaire");

        for (int i=0;i<strings.size();i++){
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.textview2,null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(strings.get(i));
            view.setTag("alpha"+i);
            llParent.addView(view);
        }

    }

    public void onTextClick(View view){
       // TextView textView = (TextView) view.findViewById(R.id.textView1);
        Toast.makeText(getApplicationContext(),"onText click",Toast.LENGTH_SHORT).show();


    }
}

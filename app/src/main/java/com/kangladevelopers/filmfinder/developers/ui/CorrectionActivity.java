package com.kangladevelopers.filmfinder.developers.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Music;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CorrectionActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correction);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Correction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onTextClick(View view){
        TextView textView = (TextView) view;
        Intent intent = new Intent(CorrectionActivity.this,MusicListActivity.class);
        intent.putExtra("data",textView.getTag().toString());
        intent.putExtra("name",textView.getText()+"");
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

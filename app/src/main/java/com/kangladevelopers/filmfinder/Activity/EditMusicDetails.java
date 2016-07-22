package com.kangladevelopers.filmfinder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kangladevelopers.filmfinder.R;

public class EditMusicDetails extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_music_details);
        mapWithXml();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void mapWithXml() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
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

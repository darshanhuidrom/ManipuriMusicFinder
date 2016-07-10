package com.kangladevelopers.filmfinder.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.pogo.Music;

public class MusicDetailActivity extends YouTubeBaseActivity {

    private Music music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_details);
        music = (Music) getIntent().getSerializableExtra("music");

    }
}

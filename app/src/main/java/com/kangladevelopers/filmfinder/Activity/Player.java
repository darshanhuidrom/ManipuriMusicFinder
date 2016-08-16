package com.kangladevelopers.filmfinder.Activity;

import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.kangladevelopers.filmfinder.utils.Utility;


public class Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    private YouTubePlayerView youtubeView;
    private String youtubeCode;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        url = getIntent().getStringExtra("url");
        youtubeCode = StringUtility.extractYouTubeCode(url);
        youtubeView.initialize(youtubeCode, Player.this);


    }

    private String[] getArray(String s) {
        String jdkl = "add,  ad, saa,scdk";
        String[] array;
        int pos = jdkl.indexOf(",");
        while (jdkl.isEmpty()) {
        }
        return null;
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            youTubePlayer.loadVideo(youtubeCode);

            // Hiding player controls
            //  player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Utility.openPlayStore(Player.this);
        youtubeView.setVisibility(View.GONE);

    }
}

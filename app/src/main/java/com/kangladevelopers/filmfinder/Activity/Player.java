package com.kangladevelopers.filmfinder.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.utils.Config;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by HUIDROM on 7/10/2016.
 */
public class Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {


    private YouTubePlayerView youtubeView;
    private String youtubeCode;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_player);
        youtubeView= (YouTubePlayerView) findViewById(R.id.youtube_view);
        url =getIntent().getStringExtra("url");
        youtubeCode= StringUtility.extractYouTubeCode(url);
       youtubeView.initialize(youtubeCode, Player.this);



    }

    private String[] getArray(String s) {
        String jdkl ="add,  ad, saa,scdk";
        String[] array;
        int pos = jdkl.indexOf(",");
        while (jdkl.isEmpty()){
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

    }
}

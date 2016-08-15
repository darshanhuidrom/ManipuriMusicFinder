package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MusicDetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private Music music;
    private YouTubePlayerView youtubeView;
    private TextView tvSongName, tvAlbum, tvCast, tvSinger, tvDirector, tvProducer, tvCompose, tvChoreographer, tvYear, tvUploadDate, tvUploadBy;
    private String youtubeCode;
    private ImageView ivThumbnail;
    private FrameLayout flThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_details);
        music = (Music) getIntent().getSerializableExtra("music");
        mapWithXml();
        setData();

    }

    private void setData() {
        if (music.getSongName().isEmpty() || music.getSongName() == null) {
            findViewById(R.id.llgv_song_name).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_song_name).setVisibility(View.VISIBLE);
            tvSongName.setText(music.getSongName());
        }
////////////////////////////////
        if (music.getMovie().isEmpty() || music.getMovie() == null) {
            findViewById(R.id.llgv_movie).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_movie).setVisibility(View.VISIBLE);
            tvAlbum.setText(music.getMovie());
        }
//////////////////////////////////
        if (music.getActor().isEmpty() || music.getActor() == null) {
            findViewById(R.id.llgv_cast).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_cast).setVisibility(View.VISIBLE);
            tvCast.setText(music.getActor());
            makeTagLinks(addSpace(music.getActor()), tvCast);
        }
//////////////////////////////////
        if (music.getSingers().isEmpty() || music.getSingers() == null) {
            findViewById(R.id.llgv_singer).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_singer).setVisibility(View.VISIBLE);
            tvSinger.setText(music.getSingers());
            makeTagLinks(addSpace(music.getSingers()), tvSinger);
        }
//////////////////////////////////////
        if (music.getDirector().isEmpty() || music.getDirector() == null) {
            findViewById(R.id.llgv_director).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_director).setVisibility(View.VISIBLE);
            tvDirector.setText(music.getDirector());
        }
        ///////////////////////////////////

        try {

            if (music.getProduction().isEmpty() || music.getProduction() == null) {
                findViewById(R.id.llgv_production).setVisibility(View.GONE);
            } else {
                findViewById(R.id.llgv_production).setVisibility(View.VISIBLE);
                tvProducer.setText(music.getProduction());
            }

        } catch (Exception e) {

        }


        if (music.getComposer().isEmpty() || music.getComposer() == null) {
            findViewById(R.id.llgv_composer).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_composer).setVisibility(View.VISIBLE);
            tvCompose.setText(music.getComposer());
        }
        ////////////////////////////////////


        if (music.getChoreographer().isEmpty() || music.getChoreographer() == null) {
            findViewById(R.id.llgv_choreographer).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_choreographer).setVisibility(View.VISIBLE);
            tvChoreographer.setText(music.getChoreographer());
        }
        //////////////////////////////////////////

        if (music.getYear() == 0) {
            findViewById(R.id.llgv_year).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_year).setVisibility(View.VISIBLE);
            tvYear.setText("" + music.getYear());
        }

        //////////////////////////////////////////////

        if (music.getUploadDate().isEmpty() || music.getUploadDate() == null) {
            findViewById(R.id.llgv_upload_date).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_upload_date).setVisibility(View.VISIBLE);
            tvUploadDate.setText(music.getUploadDate());
        }
        /////////////////////////////////////
        if (music.getUploadBy().isEmpty() || music.getUploadBy() == null) {
            findViewById(R.id.llgv_upload_by).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_upload_by).setVisibility(View.VISIBLE);
            tvUploadBy.setText(music.getUploadBy());
        }


        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.a3)
                .showImageForEmptyUri(R.drawable.a3)
                .showImageOnFail(R.drawable.a3)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(Constants.YOUTUBE_IMAGE_URL + StringUtility.extractYouTubeCode(music.getUrl()) + "/0.jpg", ivThumbnail, options);

    }

    private void mapWithXml() {
        tvSongName = (TextView) findViewById(R.id.tv_song_name);
        tvAlbum = (TextView) findViewById(R.id.tv_movie_name);
        tvCast = (TextView) findViewById(R.id.tv_cast);
        tvSinger = (TextView) findViewById(R.id.tv_singer);
        tvDirector = (TextView) findViewById(R.id.tv_director);
        tvProducer = (TextView) findViewById(R.id.tv_production);
        tvCompose = (TextView) findViewById(R.id.tv_composer);
        tvChoreographer = (TextView) findViewById(R.id.tv_choreographer);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvUploadDate = (TextView) findViewById(R.id.tv_upload_date);
        tvUploadBy = (TextView) findViewById(R.id.tv_upload_by);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youtubeCode = StringUtility.extractYouTubeCode(music.getUrl());
        ivThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        flThumbnail = (FrameLayout) findViewById(R.id.fl_thumbnail);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.loadVideo(youtubeCode);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("MusicDetailActivity >>>>>>","onInitializationFailure");
        StringUtility.openPlayStore(MusicDetailActivity.this);


    }

    private void makeTagLinks(final String text, final TextView tv) {
        if (text == null || tv == null) {
            return;
        }
        final SpannableString ss = new SpannableString(text);

        final List<String> items = Arrays.asList(text.split(","));
        int start = 0, end;

        for (String item : items) {
            end = start + item.length();
            if (start < end) {
                ss.setSpan(new MyClickableSpan(item), start, end, 0);
            }
            start += item.length() + 1;//comma and space in the original text ;)
        }
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        tv.setText(ss, TextView.BufferType.SPANNABLE);

    }

    private String addSpace(String string) {
        String ss = string.replaceAll(",", ", ");

        return ss;

    }


    private class MyClickableSpan extends ClickableSpan {
        private String mText;

        private MyClickableSpan(final String text) {
            mText = text;
        }

        @Override
        public void onClick(final View widget) {
            Toast.makeText(getApplicationContext(), mText, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MusicDetailActivity.this, BioDataActivity.class);
            intent.putExtra("name", mText);
            startActivity(intent);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(getResources().getColor(R.color.dirty_yellow));
            ds.setUnderlineText(false);
        }
    }


    public void onPlay(View view) {

        youtubeView.setVisibility(View.VISIBLE);
        ivThumbnail.setVisibility(View.GONE);
        flThumbnail.setVisibility(View.GONE);
        youtubeView.initialize(youtubeCode, this);
    }

    public void onBack(View view) {
        finish();
    }

    public void onEditMusicDetails(View view) {

        Intent intent = new Intent(this, EditMusicDetails.class);
        intent.putExtra("music", music);
        startActivity(intent);

    }
}

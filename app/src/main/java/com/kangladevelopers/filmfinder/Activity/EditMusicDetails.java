package com.kangladevelopers.filmfinder.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.ProgressBarConfig;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.pogo.SimpleResponse;
import com.kangladevelopers.filmfinder.retrofit.adapter.MusicRestAdapter;
import com.kangladevelopers.filmfinder.utils.FileFetcher;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.kangladevelopers.filmfinder.utils.Utility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;

public class EditMusicDetails extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youtubeView;
    private String youtubeCode;
    private ImageView ivThumbnail;
    private FrameLayout flThumbnail;

    private ArrayList<String> dataList;
    private String actors;
    private String singers;
    private LinearLayout llParent;
    private LinearLayout llCastParent;
    private LinearLayout llSingerParent;

    private EditText etSongName, etMovie, etDirector, etComposer, etChereographer;

    private AutoCompleteTextView actvCast;
    private AutoCompleteTextView actvSinger;
    private ArrayList<View> actorViews, singerViews;
    private String[] actorListFromDb, singerListFromDb;
    HashMap<String, String> actorMap = new HashMap<>();
    private HashMap<String, String> singerMap = new HashMap<>();
    private Music music;
    private boolean isFromDeveloper;
    // private EditText etType;
    private EditText etLyrics;
    private RadioGroup rgType;
    private RadioButton rbAlbum, rbMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_music_details);
        music = (Music) getIntent().getSerializableExtra("music");
        isFromDeveloper = getIntent().getBooleanExtra("is_from_developer", false);
        actors = music.getActor();
        singers = music.getSingers();
        mapWithXml();
        initializeData();

        addCastView();
        addSingerView();

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

    private void initializeData() {


        actorListFromDb = FileFetcher.getActorList();
        String[] displayActor = new String[actorListFromDb.length];
        for (int i = 0; i < actorListFromDb.length; i++) {
            displayActor[i] = StringUtility.getOnlyName(actorListFromDb[i]);
            actorMap.put(displayActor[i], actorListFromDb[i]);
        }
        ArrayAdapter actorAdapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, displayActor);
        actvCast.setAdapter(actorAdapter);
        actvCast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.PERSON_ICON_PIC_URL + actvCast.getText().toString().trim() + Constants.IMAGE_FORMAT;
                Log.d(">>>>>>", url);
                addCastView(actvCast.getText().toString());
                actvCast.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvCast.getWindowToken(), 0);
            }
        });

/// related to singers
        singerListFromDb = FileFetcher.getSingerList();
        String[] displaySinger = new String[singerListFromDb.length];
        for (int i = 0; i < singerListFromDb.length; i++) {
            displaySinger[i] = StringUtility.getOnlyName(singerListFromDb[i]);
            singerMap.put(displayActor[i], singerListFromDb[i]);
        }
        ArrayAdapter singerAdapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, displaySinger);
        actvSinger.setAdapter(singerAdapter);
        actvSinger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.PERSON_ICON_PIC_URL + actvCast.getText().toString().trim() + Constants.IMAGE_FORMAT;
                Log.d(">>>>>>", url);
                addSingerView(actvSinger.getText().toString());
                actvSinger.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvSinger.getWindowToken(), 0);
            }
        });


        /// initialize some data here

        if (music.getSongName().isEmpty() || music.getSongName() == null) {
            findViewById(R.id.llgv_song_name).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_song_name).setVisibility(View.VISIBLE);
            etSongName.setText(music.getSongName());
        }
        if (isFromDeveloper) {
            findViewById(R.id.llgv_song_name).setVisibility(View.VISIBLE);
            etSongName.setText(music.getSongName());
        }

        if (music.getMovie().isEmpty() || music.getMovie() == null) {
            findViewById(R.id.llgv_movie).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_movie).setVisibility(View.VISIBLE);
            etMovie.setText(music.getMovie());
        }
        if (isFromDeveloper) {
            findViewById(R.id.llgv_movie).setVisibility(View.VISIBLE);
            etMovie.setText(music.getMovie());
        }


        if (music.getDirector().isEmpty() || music.getDirector() == null) {
            findViewById(R.id.llgv_director).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_director).setVisibility(View.VISIBLE);
            etDirector.setText(music.getDirector());
        }

        if (isFromDeveloper) {
            findViewById(R.id.llgv_director).setVisibility(View.VISIBLE);
            etDirector.setText(music.getDirector());
        }


        if (music.getComposer().isEmpty() || music.getComposer() == null) {
            findViewById(R.id.llgv_composer).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_composer).setVisibility(View.VISIBLE);
            etComposer.setText(music.getComposer());
        }

        if (isFromDeveloper) {
            findViewById(R.id.llgv_composer).setVisibility(View.VISIBLE);
            etComposer.setText(music.getComposer());
        }

        if (music.getChoreographer().isEmpty() || music.getChoreographer() == null) {
            findViewById(R.id.llgv_choreographer).setVisibility(View.GONE);
        } else {
            findViewById(R.id.llgv_choreographer).setVisibility(View.VISIBLE);
            etChereographer.setText(music.getChoreographer());
        }
        if (isFromDeveloper) {
            findViewById(R.id.llgv_choreographer).setVisibility(View.VISIBLE);
            etChereographer.setText(music.getChoreographer());
        }


        if (isFromDeveloper) {
            findViewById(R.id.llgv_lyrics).setVisibility(View.VISIBLE);
            etLyrics.setText(music.getLyrics());
        } else {
            findViewById(R.id.llgv_lyrics).setVisibility(View.GONE);
        }
       /* if(isFromDeveloper){
            findViewById(R.id.llgv_type).setVisibility(View.VISIBLE);
            etType.setText(music.getType());
        }*/
       /* else {
            findViewById(R.id.llgv_type).setVisibility(View.GONE);
        }*/
        if(music.getType().trim().equalsIgnoreCase("Album")){

            rbAlbum.setChecked(true);
        }
        else if(music.getType().trim().equalsIgnoreCase("Movie")){
            rbMovie.setChecked(true);
        }

    }

    private void mapWithXml() {
        etSongName = (EditText) findViewById(R.id.et_song_name);
        etMovie = (EditText) findViewById(R.id.et_movie_name);
        etDirector = (EditText) findViewById(R.id.et_director);
        etComposer = (EditText) findViewById(R.id.et_composer);
        etChereographer = (EditText) findViewById(R.id.et_choreographer);
        //etType = (EditText) findViewById(R.id.et_type);
        etLyrics = (EditText) findViewById(R.id.et_lyrics);
        // llParent = (LinearLayout) findViewById(R.id.ll_parent);
        llCastParent = (LinearLayout) findViewById(R.id.ll_parent2);
        llSingerParent = (LinearLayout) findViewById(R.id.ll_parent3);
        actvCast = (AutoCompleteTextView) findViewById(R.id.actv_cast);
        actvSinger = (AutoCompleteTextView) findViewById(R.id.actv_singer);
        rgType = (RadioGroup) findViewById(R.id.rg_type);
        rbAlbum = (RadioButton) findViewById(R.id.rb_album);
        rbMovie = (RadioButton) findViewById(R.id.rb_movie);

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youtubeCode = StringUtility.extractYouTubeCode(music.getUrl());
        ivThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        flThumbnail = (FrameLayout) findViewById(R.id.fl_thumbnail);
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


    private void addCastView(String data) {
        //  String url = Constants.PHOTO_URL + actvCast.getText().toString().trim() + ".jpg";
        String data1 = data.replaceAll(" ", "");
        String url = Constants.PERSON_ICON_PIC_URL + data1.trim() + Constants.IMAGE_FORMAT;
        View view = LayoutInflater.from(this).inflate(R.layout.block_actorrrrr, null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_actorrrrr);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_actorrrr);
        tvName.setText(data);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.m)
                .showImageForEmptyUri(R.mipmap.m)
                .showImageOnFail(R.mipmap.m)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(url, iv, options);
        llCastParent.addView(view);
        actorViews.add(view);

    }

    private void addSingerView(String data) {
        //  String url = Constants.PHOTO_URL + actvCast.getText().toString().trim() + ".jpg";
        String data1 = data.replaceAll(" ", "");
        String url = Constants.PERSON_ICON_PIC_URL + data1.trim() + Constants.IMAGE_FORMAT;
        View view = LayoutInflater.from(this).inflate(R.layout.block_actor, null);
        TextView tvName = (TextView) view.findViewById(R.id.tv_actor);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_actor);
        tvName.setText(data);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.m)
                .showImageForEmptyUri(R.mipmap.m)
                .showImageOnFail(R.mipmap.m)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(url, iv, options);
        llSingerParent.addView(view);
        singerViews.add(view);

    }

    private void addSingerView() {

        //  String url = Constants.PHOTO_URL + actvCast.getText().toString().trim() + ".jpg";

        singerViews = new ArrayList<>();
        String url = Constants.PERSON_ICON_PIC_URL + actvSinger.getText().toString().trim() + Constants.IMAGE_FORMAT;
        String[] arrayData = singers.split(",");
        List<String> dataList = Arrays.asList(arrayData);
        for (int i = 0; i < dataList.size(); i++) {
            addSingerView(dataList.get(i));
        }

    }

    private void addCastView() {

        //  String url = Constants.PHOTO_URL + actvCast.getText().toString().trim() + ".jpg";

        actorViews = new ArrayList<>();
        String url = Constants.PERSON_ICON_PIC_URL + actvCast.getText().toString() + Constants.IMAGE_FORMAT;
        String[] arrayData = actors.split(",");
        List<String> dataList = Arrays.asList(arrayData);
        for (int i = 0; i < dataList.size(); i++) {
            addCastView(dataList.get(i));
        }

    }


    public void onDeleteClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_delete_actorrrr:
                View viewTobeDeleted = null;
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < actorViews.size(); i++) {
                    ImageView deleteButton = (ImageView) actorViews.get(i).findViewById(R.id.iv_delete_actorrrr);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted = actorViews.get(i);
                        llCastParent.removeView(actorViews.get(i));
                    }
                }
                actorViews.remove(viewTobeDeleted);
                break;
            case R.id.iv_delete:
                View viewTobeDeleted1 = null;
                for (int i = 0; i < singerViews.size(); i++) {
                    ImageView deleteButton = (ImageView) singerViews.get(i).findViewById(R.id.iv_delete);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted1 = singerViews.get(i);
                        llSingerParent.removeView(singerViews.get(i));
                    }
                }
                singerViews.remove(viewTobeDeleted1);


        }

    }

    public void addCast(View view) {
        if (!actvCast.getText().toString().trim().isEmpty()) {
            addCastView(actvCast.getText().toString());
            actvCast.setText("");
        }
    }

    public void addSinger(View view) {
        if (!actvSinger.getText().toString().trim().isEmpty()) {
            addSingerView(actvSinger.getText().toString());
            actvSinger.setText("");
        }
    }

    public void onSubmit(View view) {

        String singerList = "";
        String actorList = "";
        int id = rgType.getCheckedRadioButtonId();
        RadioButton rb = (RadioButton) findViewById(id);
        String type = null;
        if (rb != null)
            type = rb.getText().toString();
        Log.d("EditMusicDetails", ">>>> Type is " + type);
        ProgressBarConfig.showProgressBar(this, null);


        for (int i = 0; i < singerViews.size(); i++) {
            View v = singerViews.get(i);
            TextView tvSinger = (TextView) v.findViewById(R.id.tv_actor);
            if (singerList == null) {
                singerList = tvSinger.getText().toString();
            } else {
                singerList = singerList + tvSinger.getText().toString();
            }

            if (i < singerViews.size() - 1)
                singerList = singerList + ",";
        }

        for (int i = 0; i < actorViews.size(); i++) {
            View v = actorViews.get(i);
            TextView tvActor = (TextView) v.findViewById(R.id.tv_actorrrrr);
            if (actorViews == null) {
                actorList = tvActor.getText().toString();
            } else {
                actorList = actorList + tvActor.getText().toString();
            }

            if (i < actorViews.size() - 1)
                actorList = actorList + ",";
        }


        music.setSingers(singerList);
        music.setActor(actorList);
        music.setSongName((etSongName.getText().toString().trim().isEmpty()) ? music.getSongName() : etSongName.getText().toString());
        music.setDirector((etDirector.getText().toString().trim().isEmpty()) ? music.getDirector() : etDirector.getText().toString());
        music.setComposer((etComposer.getText().toString().trim().isEmpty()) ? music.getComposer() : etComposer.getText().toString());
        if (isFromDeveloper) {
            music.setLyrics((etLyrics.getText().toString().trim().isEmpty()) ? music.getLyrics() : etLyrics.getText().toString());
            // music.setType((etType.getText().toString().trim().isEmpty()) ? music.getType() : etType.getText().toString());
        }
        if (type != null)
            music.setType(type);

        /// This is for testing purpose
        if (false) {
            Log.d("EditMusicDetails", ">>>> Testing mode.No data is sent");
            return;
        }
        Call<SimpleResponse> call = MyApplication.getResAdapter().putMusicDetails(music);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SimpleResponse aa = response.body();
                if (aa.getStatus().equals("Success")) {
                    ProgressBarConfig.dismissProgressBar();
                    Toast.makeText(EditMusicDetails.this, aa.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    ProgressBarConfig.dismissProgressBar();
                    Toast.makeText(EditMusicDetails.this, aa.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Throwable t1 = t;
                ProgressBarConfig.dismissProgressBar();
                Toast.makeText(EditMusicDetails.this, t1.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void onDelete(View view) {
        Call<SimpleResponse> call = MyApplication.getResAdapter().deleteMusic(music.getId());
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                Response<SimpleResponse> response1=response;
                Log.d("EditMusicDetail", ">>>>>>"+response.message()+"");
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {

                Throwable tt=t;
                Log.d("EditMusicDetail", ">>>>>>"+t.getMessage()+"");
            }
        });
        Log.d("EditMusicDetail", ">>>>>> onDelete");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {
            youTubePlayer.loadVideo(youtubeCode);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        Utility.openPlayStore(EditMusicDetails.this);
        youtubeView.setVisibility(View.GONE);
        ivThumbnail.setVisibility(View.VISIBLE);
        flThumbnail.setVisibility(View.VISIBLE);
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
}

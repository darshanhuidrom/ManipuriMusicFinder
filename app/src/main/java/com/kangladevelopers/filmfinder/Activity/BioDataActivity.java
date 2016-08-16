package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.kangladevelopers.filmfinder.pogo.ActingSongList;
import com.kangladevelopers.filmfinder.pogo.BioData;
import com.kangladevelopers.filmfinder.pogo.ComposingSongList;
import com.kangladevelopers.filmfinder.pogo.DirctingSongList;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.pogo.SingingSongList;
import com.kangladevelopers.filmfinder.pogo.WritigSongList;
import com.kangladevelopers.filmfinder.utils.Config;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.kangladevelopers.filmfinder.utils.Utility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class BioDataActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private Toolbar toolbar;
    private LinearLayout llParentActing, llParentSinging, llParentDirecting, llParentComposing, llParentWriting;
    private String name;
    private BioData bioData;
    private TextView tvName, tvAge, tvGender, tvOccupation, tvResidence, tvAbout,tvDob;
    private ImageView iv;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private YouTubePlayerView playerView;
    private ImageView ivThumbnail;
    private FrameLayout flThumbnail;
    private Button btThumbnail;
    private boolean wasRestored;
    private String youtubeCode;
    private YouTubePlayerView youtubeView;
    private RelativeLayout rlVideoView;
    private LinearLayout ll_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biodata);
        mapWithXml();
        name = getIntent().getStringExtra("name");

        Call<BioData> call = MyApplication.getResAdapter().getBioData(StringUtility.removeSpaceFromFirst(name));
        ProgressBarConfig.showProgressBar(this, null);
        call.enqueue(new Callback<BioData>() {
            @Override
            public void onResponse(Call<BioData> call, Response<BioData> response) {
                bioData = response.body();

                if (bioData.getStatus().equalsIgnoreCase("Error")) {
                    int pos = bioData.getMessage().indexOf(":");
                    Toast.makeText(getApplicationContext(), bioData.getMessage().substring(pos + 1), Toast.LENGTH_SHORT).show();
                    ProgressBarConfig.dismissProgressBar();
                    rlVideoView.setVisibility(View.GONE);
                    return;
                }
                setData();
                if (bioData.getActingSongList() == null || bioData.getActingSongList().isEmpty()) {
                    findViewById(R.id.vg_ll_acting_parent).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.vg_ll_acting_parent).setVisibility(View.VISIBLE);
                    addActorView(bioData.getActingSongList());
                }
                if (bioData.getSingingSongList() == null || bioData.getSingingSongList().isEmpty()) {
                    findViewById(R.id.vg_ll_singing_parent).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.vg_ll_singing_parent).setVisibility(View.VISIBLE);
                    addSingerView(bioData.getSingingSongList());
                }

                if (bioData.getDirctingSongList() == null || bioData.getDirctingSongList().isEmpty()) {
                    findViewById(R.id.vg_ll_directing_parent).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.vg_ll_directing_parent).setVisibility(View.VISIBLE);
                    addDirectorView(bioData.getDirctingSongList());
                }

                if (bioData.getComposingSongList() == null || bioData.getComposingSongList().isEmpty()) {
                    findViewById(R.id.vg_ll_composing_parent).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.vg_ll_composing_parent).setVisibility(View.VISIBLE);
                    addComposerView(bioData.getComposingSongList());
                }

                if (bioData.getWritigSongList() == null || bioData.getWritigSongList().isEmpty()) {
                    findViewById(R.id.vg_ll_writing_parent).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.vg_ll_writing_parent).setVisibility(View.VISIBLE);
                    addWritingView(bioData.getWritigSongList());
                }
                ProgressBarConfig.dismissProgressBar();


                if(bioData.getData().getDbNick()==null||bioData.getData().getDbNick().trim().isEmpty()){
                    rlVideoView.setVisibility(View.GONE);
                }
                else {
                    rlVideoView.setVisibility(View.VISIBLE);
                }
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
                String name2 = name.replaceAll(" ", "");
                imageLoader.displayImage(Constants.PERSON_ICON_PIC_URL + name2.trim() + ".JPG", iv, options);

                ImageLoader imageLoader2 = ImageLoader.getInstance();
                DisplayImageOptions options2 = new DisplayImageOptions.Builder()
                        .showImageOnLoading(R.mipmap.m)
                        .showImageForEmptyUri(R.mipmap.m)
                        .showImageOnFail(R.mipmap.m)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .considerExifParams(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .build();
                String s = bioData.getData().getDbNick();
                Log.d("BioDataActivity>>>>",bioData.getData().getDbNick());

                imageLoader2.displayImage(Constants.YOUTUBE_IMAGE_URL + StringUtility.extractYouTubeCode(bioData.getData().getDbNick()) + "/0.jpg", ivThumbnail, options2);

            }

            @Override
            public void onFailure(Call<BioData> call, Throwable t) {
                Toast.makeText(BioDataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                ProgressBarConfig.dismissProgressBar();
            }
        });


    }


    private void setListener() {
        btThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(), "onclick", Toast.LENGTH_SHORT).show();
                playerView.setVisibility(View.VISIBLE);
                ivThumbnail.setVisibility(View.GONE);
                flThumbnail.setVisibility(View.GONE);
                playerView.initialize(Config.DEVELOPER_KEY, BioDataActivity.this);
            }
        });
    }
    private void setData() {
        //tvName.setText("APPLE");
        String fullName="NA";
        if(bioData.getData().getFullName()!=null){
            if(bioData.getData().getFullName().trim().isEmpty()){
                fullName = bioData.getData().getName();
            }
            else {
                fullName = bioData.getData().getFullName();
            }

        }
        tvName.setText( fullName + "");
        tvAge.setText(bioData.getData().getDYear() + "");
        tvGender.setText(bioData.getData().getGender() + "");
        tvOccupation.setText(bioData.getData().getType() + "");
        tvResidence.setText(bioData.getData().getBirthLocation() + "");
        tvAbout.setText(bioData.getData().getAbout() + "");
        if(bioData.getData().getDOB().trim().isEmpty()){
            ll_dob.setVisibility(View.GONE);
        }
        else{
            ll_dob.setVisibility(View.VISIBLE);
            tvDob.setText(bioData.getData().getDOB());
        }
    }

    private void mapWithXml() {
        llParentActing = (LinearLayout) findViewById(R.id.ll_acting_parent);
        llParentSinging = (LinearLayout) findViewById(R.id.ll_singing_parent);
        llParentDirecting = (LinearLayout) findViewById(R.id.ll_directing_parent);
        llParentComposing = (LinearLayout) findViewById(R.id.ll_composing_parent);
        llParentWriting = (LinearLayout) findViewById(R.id.ll_writing_parent);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv = (ImageView) findViewById(R.id.iv);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvAge = (TextView) findViewById(R.id.tv_age);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvOccupation = (TextView) findViewById(R.id.tv_desgn);
        tvResidence = (TextView) findViewById(R.id.tv_residence);
        tvAbout = (TextView) findViewById(R.id.tv_about);
        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        ivThumbnail = (ImageView) findViewById(R.id.iv_thumbnail);
        flThumbnail = (FrameLayout) findViewById(R.id.fl_thumbnail);
        rlVideoView = (RelativeLayout) findViewById(R.id.rl_video_view);
        tvDob= (TextView) findViewById(R.id.tv_dob);
        ll_dob= (LinearLayout) findViewById(R.id.ll_dob);

    }

    public void addActorView(List<ActingSongList> list) {
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.textview2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(list.get(i).getSongName());
            textView.setTag(list.get(i).getId());
            llParentActing.addView(view);
        }
    }

    public void addSingerView(List<SingingSongList> list) {
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.textview2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(list.get(i).getSongName());
            textView.setTag(list.get(i).getId());
            llParentSinging.addView(view);
        }
    }

    public void addDirectorView(List<DirctingSongList> list) {
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.textview2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(list.get(i).getSongName());
            textView.setTag(list.get(i).getId());
            llParentDirecting.addView(view);
        }
    }

    public void addComposerView(List<ComposingSongList> list) {
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.textview2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(list.get(i).getSongName());
            textView.setTag(list.get(i).getId());
            llParentComposing.addView(view);
        }
    }

    public void addWritingView(List<WritigSongList> list) {
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.textview2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(list.get(i).getSongName());
            textView.setTag(list.get(i).getId());
            llParentWriting.addView(view);
        }
    }


    public void onSongClick(View v) {
        TextView textView = (TextView) v;
        Toast.makeText(getApplicationContext(), "name:: " + textView.getText() + "id:: " + textView.getTag(), Toast.LENGTH_SHORT).show();

        Call<Music> call = MyApplication.getResAdapter().getMusicDetails(textView.getTag().toString());
        call.enqueue(new Callback<Music>() {
            @Override
            public void onResponse(Call<Music> call, Response<Music> response) {
                Music music = response.body();
                Intent intent = new Intent(BioDataActivity.this, MusicDetailActivity.class);
                intent.putExtra("music", music);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Music> call, Throwable t) {

            }
        });

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

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if (!b) {
            youTubePlayer.loadVideo(StringUtility.extractYouTubeCode(bioData.getData().getDbNick()));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Utility.openPlayStore(BioDataActivity.this);
        youtubeView.setVisibility(View.GONE);
        ivThumbnail.setVisibility(View.VISIBLE);
        flThumbnail.setVisibility(View.VISIBLE);
    }

    public void onPlay(View view) {

        youtubeView.setVisibility(View.VISIBLE);
        ivThumbnail.setVisibility(View.GONE);
        flThumbnail.setVisibility(View.GONE);
        youtubeView.initialize(StringUtility.extractYouTubeCode(bioData.getData().getDbNick()), this);
    }

    public void onBack(View view){
        onBackPressed();
    }
}

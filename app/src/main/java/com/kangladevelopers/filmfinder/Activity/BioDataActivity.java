package com.kangladevelopers.filmfinder.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class BioDataActivity extends BaseActivity {

    private Toolbar toolbar;
    private LinearLayout llParentActing, llParentSinging, llParentDirecting, llParentComposing, llParentWriting;
    private String name;
    private BioData bioData;
    private TextView tvName, tvAge, tvGender, tvOccupation, tvResidence, tvAbout;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biodata);

        mapWithXml();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = getIntent().getStringExtra("name");

        Call<BioData> call = MyApplication.getResAdapter().getBioData(name);
        ProgressBarConfig.showProgressBar(this, null);
        call.enqueue(new Callback<BioData>() {
            @Override
            public void onResponse(Call<BioData> call, Response<BioData> response) {
                bioData = response.body();
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
            }

            @Override
            public void onFailure(Call<BioData> call, Throwable t) {
                Toast.makeText(BioDataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                ProgressBarConfig.dismissProgressBar();
            }
        });
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
        imageLoader.displayImage(Constants.BIO_DATA__ICON_PIC_URL + name.trim() + ".JPG", iv, options);
    }

    private void setData() {
        //tvName.setText("APPLE");
        String fullName="NA";
        if(bioData.getData().getFullName()!=null){
            fullName = bioData.getData().getFullName();
        }
        tvName.setText( fullName + "");
        tvAge.setText(bioData.getData().getDYear() + "");
        tvGender.setText(bioData.getData().getGender() + "");
        tvOccupation.setText(bioData.getData().getType() + "");
        tvResidence.setText(bioData.getData().getBirthLocation() + "");
        tvAbout.setText(bioData.getData().getAbout() + "");
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
}

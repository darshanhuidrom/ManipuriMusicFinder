package com.kangladevelopers.filmfinder.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.ProgressBarConfig;
import com.kangladevelopers.filmfinder.pogo.BioData;
import com.kangladevelopers.filmfinder.pogo.SongList;
import com.kangladevelopers.filmfinder.retrofit.adapter.MusicRestAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HUIDROM on 7/17/2016.
 */
public class BioDataActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout llParent;
    private MusicRestAdapter restAdapter;
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

        restAdapter = new MusicRestAdapter();
        Call<BioData> call = restAdapter.getBioData(name);
        ProgressBarConfig.showProgressBar(this, null);
        call.enqueue(new Callback<BioData>() {
            @Override
            public void onResponse(Call<BioData> call, Response<BioData> response) {
                bioData = response.body();
                setData();
                addView(bioData.getSongList());
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
        tvName.setText(bioData.getData().getFullName() + "");
        tvAge.setText(bioData.getData().getDYear() + "");
        tvGender.setText(bioData.getData().getGender() + "");
        tvOccupation.setText(bioData.getData().getType() + "");
        tvResidence.setText(bioData.getData().getBirthLocation() + "");
        tvAbout.setText(bioData.getData().getAbout() + "");
    }

    private void mapWithXml() {
        llParent = (LinearLayout) findViewById(R.id.ll_parent);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        iv = (ImageView) findViewById(R.id.iv);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvAge = (TextView) findViewById(R.id.tv_age);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvOccupation = (TextView) findViewById(R.id.tv_desgn);
        tvResidence = (TextView) findViewById(R.id.tv_residence);
        tvAbout = (TextView) findViewById(R.id.tv_about);
        addView(new ArrayList<SongList>());

    }

    public void addView(List<SongList> list) {
        for (int i = 0; i < list.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.textview2, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(list.get(i).getSongName());
            textView.setTag(list.get(i).getId());
            llParent.addView(view);
        }

    }


    public void onSongClick(View v) {
        TextView textView = (TextView) v;
        Toast.makeText(getApplicationContext(), "name:: " + textView.getText() + "id:: " + textView.getTag(), Toast.LENGTH_SHORT).show();

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

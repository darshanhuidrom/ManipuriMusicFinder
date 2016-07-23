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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.pogo.SimpleResponse;
import com.kangladevelopers.filmfinder.retrofit.adapter.MusicRestAdapter;
import com.kangladevelopers.filmfinder.utils.StringUtility;
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

public class EditMusicDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<String> dataList;
    private String actors;
    private String singers;
    private LinearLayout llParent;
    private LinearLayout llCastParent;
    private LinearLayout llSingerParent;

    private AutoCompleteTextView actvCast;
    private AutoCompleteTextView actvSinger;
    private ArrayList<View> actorViews,singerViews;
    private String[] actorListFromDb,singerListFromDb;
    HashMap<String, String> actorMap = new HashMap<>();
    private HashMap<String, String> singerMap=new HashMap<>();
    private Music music;
    MusicRestAdapter musicRestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_music_details);
        dataList = getIntent().getStringArrayListExtra("data");
        actors = getIntent().getStringExtra("actors");
        singers = getIntent().getStringExtra("singers");
        music= (Music) getIntent().getSerializableExtra("music");
        mapWithXml();
        initializeData();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addView(dataList);
        addCastView();
        addSingerView();
        musicRestAdapter = new MusicRestAdapter();

    }

    private void initializeData() {


        actorListFromDb = StringUtility.getActorList();
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
                String url = Constants.SINGER_URL + actvCast.getText().toString().trim() + ".jpg";
                Log.d(">>>>>>", url);
                addCastView(actvCast.getText().toString());
                actvCast.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvCast.getWindowToken(), 0);
            }
        });

/// related to singers
        singerListFromDb = StringUtility.getSingerList();
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
                String url = Constants.SINGER_URL + actvCast.getText().toString().trim() + ".jpg";
                Log.d(">>>>>>", url);
                addSingerView(actvSinger.getText().toString());
                actvSinger.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvSinger.getWindowToken(), 0);
            }
        });


    }

    private void mapWithXml() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        llParent = (LinearLayout) findViewById(R.id.ll_parent);
        llCastParent = (LinearLayout) findViewById(R.id.ll_parent2);
        llSingerParent= (LinearLayout) findViewById(R.id.ll_parent3);
        actvCast = (AutoCompleteTextView) findViewById(R.id.actv_cast);
        actvSinger= (AutoCompleteTextView) findViewById(R.id.actv_singer);
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

    private void addView(ArrayList<String> dataList) {

        for (int i = 0; i < dataList.size(); i++) {
            int pos = dataList.get(i).indexOf("#");
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.edit_music_details_block, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_field);
            EditText et = (EditText) view.findViewById(R.id.et_field_value);
            tv.setText(dataList.get(i).substring(0, pos) + ":");
            et.setText(dataList.get(i).substring(pos + 1));
            llParent.addView(view);


        }

    }

    private void addCastView(String data) {
        //  String url = Constants.SINGER_URL + actvCast.getText().toString().trim() + ".jpg";
        String url = Constants.SINGER_URL + "SorriSenjam" + ".jpg";
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
        //  String url = Constants.SINGER_URL + actvCast.getText().toString().trim() + ".jpg";
        String url = Constants.SINGER_URL + "SorriSenjam" + ".jpg";
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

        //  String url = Constants.SINGER_URL + actvCast.getText().toString().trim() + ".jpg";

        singerViews = new ArrayList<>();
        String url = Constants.SINGER_URL + "SorriSenjam" + ".jpg";
        String[] arrayData = singers.split(",");
        List<String> dataList = Arrays.asList(arrayData);
        for (int i = 0; i < dataList.size(); i++) {
            addSingerView(dataList.get(i));
        }

    }

    private void addCastView() {

        //  String url = Constants.SINGER_URL + actvCast.getText().toString().trim() + ".jpg";

        actorViews = new ArrayList<>();
        String url = Constants.SINGER_URL + "SorriSenjam" + ".jpg";
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

    public void addCast(View view){
        if(!actvCast.getText().toString().trim().isEmpty()){
            addCastView(actvCast.getText().toString());
            actvCast.setText("");
        }
    }

    public void addSinger(View view){
        if(!actvSinger.getText().toString().trim().isEmpty()){
            addSingerView(actvSinger.getText().toString());
            actvSinger.setText("");
        }
    }

    public void onSubmit(View view){




        Call <SimpleResponse> call= musicRestAdapter.putMusicDetails(music);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SimpleResponse aa= response.body();
            }


            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Throwable t1 = t;

            }
        });

    }

}

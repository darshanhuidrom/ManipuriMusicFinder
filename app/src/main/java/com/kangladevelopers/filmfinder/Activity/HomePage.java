package com.kangladevelopers.filmfinder.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.RvMusicAdapter;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.AppPreference;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.LogMessage;
import com.kangladevelopers.filmfinder.Utility.PopUpDialog;
import com.kangladevelopers.filmfinder.Utility.ProgressBarConfig;
import com.kangladevelopers.filmfinder.developers.ui.DeveloperActivity;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.retrofit.adapter.MusicRestAdapter;
import com.kangladevelopers.filmfinder.storage.LocalStore;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends BaseDrawerActivity {
    private LinearLayout llSingerCondition, llComposerCondition, llDirectorCondition, llActorCondition;
    private LinearLayout llSingerParentLayout, llComposerParentLayout, llDirectorParentLayout, llActorParentLayout;
    AutoCompleteTextView actvSinger, actvComposer, actvDirector, actvActor;
    TextView tvNoDataFound;
    TextView tvUserName;
    ImageView ivProfileImage;
    ArrayList<View> viewSingerList = new ArrayList<>();
    ArrayList<View> viewComposerList = new ArrayList<>();
    ArrayList<View> viewDirectorList = new ArrayList<>();
    ArrayList<View> viewActor = new ArrayList<>();
    TextView tvSingerCount, tvComposerCount, tvDirectorCount, tvActorCount;
    MusicRestAdapter musicRestAdapter;
    private Calendar calendar;
    RecyclerView rvMusic;
    int mDD, mMM, mYY;
    TextView btStartDate, btEndDate;
    int SELECTOR;
    private LinearLayout llTypeCondition2;
    ArrayList<Integer> drawerTabIds;
    RvMusicAdapter musicAdapter;
    Switch swSinger;
    private String[] singers;
    HashMap<String, String> singerMap = new HashMap<>();
    HashMap<String, String> composeMapMap = new HashMap<>();
    HashMap<String, String> directorMap = new HashMap<>();
    HashMap<String, String> actorMap = new HashMap<>();
    private String[] directors;
    private String[] composer;
    private String[] actor;
    private static final int CAMERA_REQUEST = 1888;
    private static int OPEN_GALLEY_REQUEST_CODE = 121;
    private static int SIGN_IN_REQUEST = 111;
    private String[] displayData;
    private String[] displayComposer;
    private String[] displayDirector;
    private String[] displayActor;
    private LinearLayout llComParent;
    private LinearLayout llDirParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);
        setWidget();
        if (isSignedIn()) {
            tvUserName.setText(AppPreference.getDataFromAppPreference(getApplicationContext(), Constants.USER_NAME));
        } else {
            tvUserName.setText("Sign In");
        }
        try {
            LocalStore.loadImageFromStorage(ivProfileImage);
        } catch (Exception e) {

        }
        initializeData();
        setListeners();
        setDrawer();
        setCurrentDate();
        getDelegate().getSupportActionBar().setTitle("Move Finder");
    }

    private void setCurrentDate() {
        calendar = Calendar.getInstance();
        mDD = calendar.get(Calendar.DAY_OF_MONTH);
        mMM = calendar.get(Calendar.MONTH);
        mYY = calendar.get(Calendar.YEAR);
    }



    private void setListeners() {
        actvSinger.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.PERSON_ICON_PIC_URL + actvSinger.getText().toString().trim() +Constants.IMAGE_FORMAT;
                Log.d(">>>>>>", url);
                addSingerView(actvSinger.getText().toString(), url.replace(" ", ""));
                actvSinger.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvSinger.getWindowToken(), 0);


            }
        });
        actvComposer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String url = Constants.PERSON_ICON_PIC_URL + actvComposer.getText().toString().trim() + Constants.IMAGE_FORMAT;
                Log.d(">>>>>>", url);
                if (viewComposerList.size() == 0) {
                    addComposerView(actvComposer.getText().toString(), url.replace(" ", ""));
                    actvComposer.setText("");
                    llComParent.setVisibility(View.GONE);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvComposer.getWindowToken(), 0);

            }


        });
        actvDirector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.PERSON_ICON_PIC_URL + actvDirector.getText().toString().trim()+Constants.IMAGE_FORMAT;
                Log.d(">>>>>>", url);
                if (viewDirectorList.size() == 0) {
                    addDirectorView(actvDirector.getText().toString(), url.replace(" ", ""));
                    actvDirector.setText("");
                    llDirParent.setVisibility(View.GONE);
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvDirector.getWindowToken(), 0);


            }
        });
        actvActor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.PERSON_ICON_PIC_URL + actvActor.getText().toString().trim() +Constants.IMAGE_FORMAT;
                Log.d(">>>>>>", url);
                addActorView(actvActor.getText().toString(), url.replace(" ", ""));
                actvActor.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(actvActor.getWindowToken(), 0);

            }
        });

    }

    private void initializeData() {
        musicRestAdapter = new MusicRestAdapter();
        singers = StringUtility.getSingerList();
        displayData = new String[singers.length];
        for (int i = 0; i < singers.length; i++) {
            displayData[i] = StringUtility.getOnlyName(singers[i]);
            singerMap.put(displayData[i], singers[i]);
        }

        ////////////////////////////////////////////////////////////////////////////
        composer = StringUtility.getComposer();
        displayComposer = new String[composer.length];
        for (int i = 0; i < composer.length; i++) {
            displayComposer[i] = StringUtility.getOnlyName(composer[i]);
            composeMapMap.put(displayComposer[i], composer[i]);
        }

        ///////////////////////////////////////////////////////////////////////////////
        directors = StringUtility.getDirectorList();
        displayDirector = new String[directors.length];
        for (int i = 0; i < directors.length; i++) {
            displayDirector[i] = StringUtility.getOnlyName(directors[i]);
            directorMap.put(displayDirector[i], directors[i]);
        }

        ////////////////////////////////////////////////////////////////////
        actor = StringUtility.getActorList();
        displayActor = new String[actor.length];
        for (int i = 0; i < actor.length; i++) {
            displayActor[i] = StringUtility.getOnlyName(actor[i]);
            actorMap.put(displayActor[i], actor[i]);
        }


        calendar = Calendar.getInstance();
        ArrayAdapter singerAdapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, displayData);
        ArrayAdapter composerAdapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, displayComposer);
        ArrayAdapter directorAdapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, displayDirector);
        ArrayAdapter actorAdapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, displayActor);
        actvSinger.setAdapter(singerAdapter);
        actvComposer.setAdapter(composerAdapter);
        actvActor.setAdapter(actorAdapter);

        actvDirector.setAdapter(directorAdapter);
        if (drawerTabIds == null) {
            drawerTabIds = new ArrayList<>();
            drawerTabIds.add(R.id.ll_castCondition);
            drawerTabIds.add(R.id.ll_directorCondition);
            drawerTabIds.add(R.id.ll_typeCondition);
            drawerTabIds.add(R.id.ll_typeCondition11);
            drawerTabIds.add(R.id.ll_timeCondition);
        }
        hideAndUnhideActor();
        hideAndUnhideCondition();
        hideAndUnhideDirector();
        hideAndUnhideYear();
        hideAndUnhideActorrr();
        hideNoDataFound();
    }

    private void setWidget() {
        llSingerCondition = (LinearLayout) findViewById(R.id.ll_castCondition);
        llComposerCondition = (LinearLayout) findViewById(R.id.ll_directorCondition);
        llDirectorCondition = (LinearLayout) findViewById(R.id.ll_typeCondition);
        llTypeCondition2 = (LinearLayout) findViewById(R.id.ll_typeCondition11);
        llActorCondition = (LinearLayout) findViewById(R.id.ll_timeCondition);
        llComposerParentLayout = (LinearLayout) findViewById(R.id.ll_director_parent_layout);
        llSingerParentLayout = (LinearLayout) findViewById(R.id.ll_actor_parent_layout);
        llDirectorParentLayout = (LinearLayout) findViewById(R.id.ll_director_parent_layoutttt);
        llActorParentLayout = (LinearLayout) findViewById(R.id.ll_actor_parent_layoutttt);
        actvSinger = (AutoCompleteTextView) findViewById(R.id.actv_actor);
        actvComposer = (AutoCompleteTextView) findViewById(R.id.actv_director);
        actvDirector = (AutoCompleteTextView) findViewById(R.id.actv_directorrrr);
        actvActor = (AutoCompleteTextView) findViewById(R.id.actv_actorrr);
        tvSingerCount = (TextView) findViewById(R.id.tv_actors_count);
        tvComposerCount = (TextView) findViewById(R.id.tv_director_count);
        tvDirectorCount = (TextView) findViewById(R.id.tv_director_counttt);
        tvActorCount = (TextView) findViewById(R.id.tv_actor_counttt);
        rvMusic = (RecyclerView) findViewById(R.id.rv_moveList);
        btStartDate = (TextView) findViewById(R.id.bt_start_date);
        btEndDate = (TextView) findViewById(R.id.bt_end_date);
        swSinger = (Switch) findViewById(R.id.sw_singer);
        tvNoDataFound = (TextView) findViewById(R.id.tv_no_data_found);
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        ivProfileImage = (ImageView) findViewById(R.id.iv_profile_image);
        llComParent=(LinearLayout) findViewById(R.id.ll_com_parent);
        llDirParent=(LinearLayout) findViewById(R.id.ll_dir_parent);

    }

    private void hideNoDataFound() {
        tvNoDataFound.setVisibility(View.GONE);
        rvMusic.setVisibility(View.VISIBLE);
    }

    private void unHideNoDataFound() {
        tvNoDataFound.setVisibility(View.VISIBLE);
        rvMusic.setVisibility(View.GONE);
    }


    public void filterConditionClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_cast:
                //   Toast.makeText(getApplicationContext(), "filterConditionClick", Toast.LENGTH_LONG).show();
                hideOthers(R.id.ll_castCondition);
                break;
            case R.id.rl_director:
                hideOthers(R.id.ll_directorCondition);
                break;
            case R.id.rl_type:
                hideOthers(R.id.ll_typeCondition);
                break;
            case R.id.rl_other:
                hideOthers(R.id.ll_typeCondition11);
                break;
            case R.id.rl_year:
                hideOthers(R.id.ll_timeCondition);
                break;
            default:
                //  Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
        }
    }

    private void hideOthers(int id) {
        for (int i = 0; i < drawerTabIds.size(); i++) {
            if (drawerTabIds.get(i) == id) {

            } else {
                findViewById(drawerTabIds.get(i)).setVisibility(View.GONE);
            }
        }
        final int iddd = id;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                hideAndUnhideDrawerTab(iddd);
            }
        };
        handler.postDelayed(runnable, 700);


    }


    public void onDeleteClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_delete:
                View viewTobeDeleted = null;
                // Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewSingerList.size(); i++) {
                    ImageView deleteButton = (ImageView) viewSingerList.get(i).findViewById(R.id.iv_delete);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted = viewSingerList.get(i);
                        llSingerParentLayout.removeView(viewSingerList.get(i));
                    }
                }
                viewSingerList.remove(viewTobeDeleted);
                if (viewSingerList.size() == 0) {
                    tvSingerCount.setVisibility(View.INVISIBLE);
                } else {
                    tvSingerCount.setVisibility(View.VISIBLE);
                }
                tvSingerCount.setText("" + viewSingerList.size());
                break;
            case R.id.iv_delete_director:
                View viewTobeDeleted2 = null;
                // Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewComposerList.size(); i++) {
                    ImageView deleteButton = (ImageView) viewComposerList.get(i).findViewById(R.id.iv_delete_director);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted2 = viewComposerList.get(i);
                        llComposerParentLayout.removeView(viewComposerList.get(i));
                    }
                }
                viewComposerList.remove(viewTobeDeleted2);
                if (viewComposerList.size() == 0) {
                    tvComposerCount.setVisibility(View.GONE);
                } else {
                    tvComposerCount.setVisibility(View.VISIBLE);
                }
                llComParent.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_delete_directorrr:
                View viewTobeDeleted3 = null;
                // Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewDirectorList.size(); i++) {
                    ImageView deleteButton = (ImageView) viewDirectorList.get(i).findViewById(R.id.iv_delete_directorrr);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted3 = viewDirectorList.get(i);
                        llDirectorParentLayout.removeView(viewDirectorList.get(i));
                    }
                }
                viewDirectorList.remove(viewTobeDeleted3);
                if (viewDirectorList.size() == 0) {
                    tvDirectorCount.setVisibility(View.GONE);
                } else {
                    tvDirectorCount.setVisibility(View.VISIBLE);
                }
                llDirParent.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_delete_actorrrr:
                View viewTobeDeleted4 = null;
                // Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewActor.size(); i++) {
                    ImageView deleteButton = (ImageView) viewActor.get(i).findViewById(R.id.iv_delete_actorrrr);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted4 = viewActor.get(i);
                        llActorParentLayout.removeView(viewActor.get(i));
                    }
                }
                viewActor.remove(viewTobeDeleted4);
                if (viewActor.size() == 0) {
                    tvActorCount.setVisibility(View.GONE);
                } else {
                    tvActorCount.setVisibility(View.VISIBLE);
                }
                tvActorCount.setText("" + viewDirectorList.size());
                llDirParent.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void hideAndUnhideActor() {
        if (llSingerCondition.getVisibility() == View.GONE)
            llSingerCondition.setVisibility(View.VISIBLE);
        else {
            llSingerCondition.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideDirector() {
        if (llComposerCondition.getVisibility() == View.GONE)
            llComposerCondition.setVisibility(View.VISIBLE);
        else {
            llComposerCondition.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideYear() {
        if (llActorCondition.getVisibility() == View.GONE)
            llActorCondition.setVisibility(View.VISIBLE);
        else {
            llActorCondition.setVisibility(View.GONE);
        }
    }


    private void hideAndUnhideCondition() {
        if (llDirectorCondition.getVisibility() == View.GONE)
            llDirectorCondition.setVisibility(View.VISIBLE);
        else {
            llDirectorCondition.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideActorrr() {
        if (llTypeCondition2.getVisibility() == View.GONE)
            llTypeCondition2.setVisibility(View.VISIBLE);
        else {
            llTypeCondition2.setVisibility(View.GONE);
        }
    }

    private void hideAndUnhideDrawerTab(int id) {
        View view = findViewById(id);
        if (view.getVisibility() != View.GONE)
            view.setVisibility(View.GONE);

        else {
            view.setVisibility(View.VISIBLE);
        }
    }


    private void addSingerView(String actorNamee, String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.block_actor, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_actor);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_actor);
        viewSingerList.add(view);
        llSingerParentLayout.addView(view);

        if (viewSingerList.size() == 0) {
            tvSingerCount.setVisibility(View.INVISIBLE);
        } else {
            tvSingerCount.setVisibility(View.VISIBLE);
        }
        tvSingerCount.setText("" + viewSingerList.size());
        actorName.setText(actorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (StringUtility.isMale(singerMap.get(actorNamee))) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.m)
                    .showImageForEmptyUri(R.mipmap.m)
                    .showImageOnFail(R.mipmap.m)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        } else {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.f)
                    .showImageForEmptyUri(R.mipmap.f)
                    .showImageOnFail(R.mipmap.f)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        }


    }

    private void addComposerView(String DirectorNamee, String imageUrl) {

        final View view = LayoutInflater.from(this).inflate(R.layout.block_directer, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_director);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_director);
        viewComposerList.add(view);
        llComposerParentLayout.addView(view);
        if (viewComposerList.size() == 0) {
            tvComposerCount.setVisibility(View.GONE);
        } else {
            tvComposerCount.setVisibility(View.VISIBLE);
        }
        actorName.setText(DirectorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (StringUtility.isMale(composeMapMap.get(DirectorNamee))) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.m)
                    .showImageForEmptyUri(R.mipmap.m)
                    .showImageOnFail(R.mipmap.m)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        } else {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.f)
                    .showImageForEmptyUri(R.mipmap.f)
                    .showImageOnFail(R.mipmap.f)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        }
    }


    private void addDirectorView(String DirectorNamee, String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.block_directorrr, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_directorrrr);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_directorrrr);
        viewDirectorList.add(view);
        llDirectorParentLayout.addView(view);
        if (viewDirectorList.size() == 0) {
            tvDirectorCount.setVisibility(View.GONE);
        } else {
            tvDirectorCount.setVisibility(View.VISIBLE);
        }
        actorName.setText(DirectorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (StringUtility.isMale(directorMap.get(DirectorNamee))) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.m)
                    .showImageForEmptyUri(R.mipmap.m)
                    .showImageOnFail(R.mipmap.m)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        } else {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.f)
                    .showImageForEmptyUri(R.mipmap.f)
                    .showImageOnFail(R.mipmap.f)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        }
    }

    private void addActorView(String DirectorNamee, String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.block_actorrrrr, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_actorrrrr);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_actorrrr);
        viewActor.add(view);
        llActorParentLayout.addView(view);
        if (viewActor.size() == 0) {
            tvActorCount.setVisibility(View.GONE);
        } else {
            tvActorCount.setVisibility(View.VISIBLE);
        }
        tvActorCount.setText("" + viewActor.size());
        actorName.setText(DirectorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (StringUtility.isMale(actorMap.get(DirectorNamee))) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.m)
                    .showImageForEmptyUri(R.mipmap.m)
                    .showImageOnFail(R.mipmap.m)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        } else {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.f)
                    .showImageForEmptyUri(R.mipmap.f)
                    .showImageOnFail(R.mipmap.f)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
            imageLoader.displayImage(imageUrl, actorImage, options);
        }
    }

    private void searchMovies() {
        String singerList = null;
        String composerList = null;
        String directorList = null;
        String actorList = null;
        String startTime;
        String endTime;
        String fixSinger;
        ProgressBarConfig.showProgressBar(this, null);
        hideNoDataFound();

        for (int i = 0; i < viewSingerList.size(); i++) {
            View view = viewSingerList.get(i);
            TextView tvActor = (TextView) view.findViewById(R.id.tv_actor);
            if (singerList == null) {
                singerList = tvActor.getText().toString();
            } else {
                singerList = singerList + tvActor.getText().toString();
            }

            if (i < viewSingerList.size() - 1)
                singerList = singerList + ",";
        }

        for (int i = 0; i < viewComposerList.size(); i++) {
            View view = viewComposerList.get(i);
            TextView tvDirector = (TextView) view.findViewById(R.id.tv_director);
            if (composerList == null) {
                composerList = tvDirector.getText().toString();
            } else {
                composerList = composerList + tvDirector.getText().toString();
            }

            if (i < viewComposerList.size() - 1)
                composerList = composerList + ",";
        }

        for (int i = 0; i < viewDirectorList.size(); i++) {
            View view = viewDirectorList.get(i);
            TextView tvDirector = (TextView) view.findViewById(R.id.tv_directorrrr);
            if (directorList == null) {
                directorList = tvDirector.getText().toString();
            } else {
                directorList = directorList + tvDirector.getText().toString();
            }

            if (i < viewDirectorList.size() - 1)
                directorList = directorList + ",";
        }

        for (int i = 0; i < viewActor.size(); i++) {
            View view = viewActor.get(i);
            TextView tvActor = (TextView) view.findViewById(R.id.tv_actorrrrr);
            if (actorList == null) {
                actorList = tvActor.getText().toString();
            } else {
                actorList = actorList + tvActor.getText().toString();
            }

            if (i < viewActor.size() - 1)
                actorList = actorList + ",";
        }

        if (swSinger.isChecked()) {
            fixSinger = "true";
        } else {
            fixSinger = "false";
        }
        startTime = btStartDate.getText().toString();
        endTime = btEndDate.getText().toString();
        String query = singerList + "&" + composerList + "&" + directorList + "&" + actorList;
        //   Toast.makeText(getApplicationContext(), "query is\n" + query, Toast.LENGTH_LONG).show();
        LogMessage.printLog(TAG, query);

        Call<List<Music>> call = musicRestAdapter.getMusicDetails(singerList, composerList, directorList, actorList, fixSinger, startTime, endTime);
        call.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                ProgressBarConfig.dismissProgressBar();
                List<Music> musics = response.body();
                if (musics == null || musics.isEmpty()) {
                    unHideNoDataFound();
                    return;
                }
                hideNoDataFound();
                Log.d(">>>>>>", musics.toString());
                if (musicAdapter == null) {
                    musicAdapter = new RvMusicAdapter(getApplicationContext(), musics);
                    musicAdapter.setRvAdapterClickLIstener(new RvMusicAdapter.RvAdapterClickListener() {
                        @Override
                        public void onItemClick(int i, View v) {
                            Intent intent = new Intent(HomePage.this, MusicDetailActivity.class);
                            intent.putExtra("music", musicAdapter.getData().get(i));
                            startActivity(intent);
                        }
                    });
                    rvMusic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvMusic.setAdapter(musicAdapter);
                } else {
                    musicAdapter.setNotifyChange(musics);
                }


            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {

                String aa = t.getMessage();
                //     Log.d(">>>>>>", aa);
                unHideNoDataFound();
                ProgressBarConfig.dismissProgressBar();
            }
        });
        mDrawerLayout.closeDrawers();
    }

    public void onSubmit(View view) {
        searchMovies();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, mYY, mMM, mDD);
        datePickerDialog.show();
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            setDate(dayOfMonth, monthOfYear + 1, year);
        }
    };

    private void setDate(int dd, int mm, int yy) {

        if (SELECTOR == 1) {
            btStartDate.setText("" + dd + "/" + mm + "/" + yy);
        } else if (SELECTOR == 2) {
            btEndDate.setText("" + dd + "/" + mm + "/" + yy);
        }

    }


    public void onStartClick(View view) {
        SELECTOR = 1;
        showDatePickerDialog();
    }

    public void onEndClick(View view) {
        SELECTOR = 2;
        showDatePickerDialog();
    }

    public void onImageClick(View view) {

        CustomDialogBox dialogBox = new CustomDialogBox(this, new CustomDialogBox.Listeners() {
            @Override
            public void onGalleryClick() {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, OPEN_GALLEY_REQUEST_CODE);
            }

            @Override
            public void onCameraClick() {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        dialogBox.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            LocalStore.saveToInternalStorage(photo);
            LocalStore.loadImageFromStorage(ivProfileImage);
        } else if (requestCode == OPEN_GALLEY_REQUEST_CODE && resultCode == RESULT_OK
                && null != data) {
            // Get the Image from data

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            // Get the cursor
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            // Move to first row
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String imgDecodableString = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(imgDecodableString);
            LocalStore.saveToInternalStorage(bitmap);
            LocalStore.loadImageFromStorage(ivProfileImage);


        } else if (requestCode == SIGN_IN_REQUEST && resultCode == RESULT_OK) {
            tvUserName.setText(AppPreference.getDataFromAppPreference(getApplicationContext(), Constants.USER_NAME));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        switch (item.getItemId()) {
            // THIS IS YOUR DRAWER/HAMBURGER BUTTON
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
                Log.d("NICK", "OPEN NAVI");
                return true;
            case R.id.lists:
                startActivity(new Intent(this, ListAllActivity.class));
                return true;
            case R.id.developer:
                Intent intent = new Intent(this, ListAllActivity.class);
                intent.putExtra("IS_FROM_DEVELOPER", true);
                startActivity(intent);
                return true;
            case R.id.developer2:
                Intent intent2 = new Intent(this, ListAllActivity.class);
                intent2.putExtra("IS_FROM_DEVELOPER", true);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isSignedIn() {
        return (boolean) AppPreference.getDataFromAppPreference(getApplicationContext(), Constants.IS_SIGNED_IN, AppPreference.MODE_BOOLEAN);
    }


    public void onUserClick(View view) {

        Intent intent = new Intent(this, SignInActivity.class);
        startActivityForResult(intent, SIGN_IN_REQUEST);
    }

    public void openSingerList(View view) {


        int id = view.getId();
        switch (id) {
            //  case
        }

        new PopUpDialog(this, displayData) {
            @Override
            public void onItemClick(String s, int pos) {
                addSingerView(s, Constants.PERSON_ICON_PIC_URL + s.trim() + Constants.IMAGE_FORMAT);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }.show();
    }


    public void openComposerList(View view) {

        new PopUpDialog(this, displayComposer) {
            @Override
            public void onItemClick(String s, int pos) {
                addComposerView(s, Constants.PERSON_ICON_PIC_URL + s.trim() +Constants.IMAGE_FORMAT);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }.show();
    }

    public void openDirectorList(View view) {


        new PopUpDialog(this, displayDirector) {
            @Override
            public void onItemClick(String s, int pos) {
                addDirectorView(s, Constants.PERSON_ICON_PIC_URL + s.trim() +Constants.IMAGE_FORMAT);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }.show();
    }


    public void openActorList(View view) {

        new PopUpDialog(this, displayActor) {
            @Override
            public void onItemClick(String s, int pos) {
                addActorView(s, Constants.PERSON_ICON_PIC_URL + s.trim() +Constants.IMAGE_FORMAT);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }.show();
    }

}

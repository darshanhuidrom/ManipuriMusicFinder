package com.kangladevelopers.filmfinder.Activity;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kangladevelopers.filmfinder.Adapter.RvMovieAdapter;
import com.kangladevelopers.filmfinder.Adapter.RvMusicAdapter;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.Utility.LogMessage;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.retrofit.adapter.ActorRestAdapter;
import com.kangladevelopers.filmfinder.retrofit.adapter.MusicRestAdapter;
import com.kangladevelopers.filmfinder.utils.StringUtility;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends BaseDrawerActivity {
    private LinearLayout llSingerCondition, llComposerCondition, llDirectorCondition, llActorCondition;
    private LinearLayout llSingerParentLayout, llComposerParentLayout, llDirectorParentLayout, llActorParentLayout;
    AutoCompleteTextView actvActor, actvDirector, director, actor;
    ArrayList<View> viewSingerList = new ArrayList<>();
    ArrayList<View> viewComposerList = new ArrayList<>();
    ArrayList<View> viewDirectorList = new ArrayList<>();
    ArrayList<View> viewActor = new ArrayList<>();
    TextView tvSingerCount, tvComposerCount, tvDirectorCount, tvActorCount;
    MusicRestAdapter musicRestAdapter;
    private Calendar calendar;
    RecyclerView rvMusic;
    int mDD, mMM, mYY;
    Button btStartDate, btEndDate;
    int SELECTOR;
    private LinearLayout llTypeCondition2;
    ArrayList<Integer> drawerTabIds;
    RvMusicAdapter musicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home_page);
        setWidget();
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
        actvActor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.SINGER_URL + actvActor.getText().toString().trim() + ".jpg";
                Log.d(">>>>>>", url);
                addActorView(actvActor.getText().toString(), url.replace(" ", ""));
                actvActor.setText("");
            }
        });
        actvDirector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.SINGER_URL + actvDirector.getText().toString().trim() + ".jpg";
                Log.d(">>>>>>", url);
                if (viewComposerList.size() == 0) {
                    addDirectorView(actvDirector.getText().toString(), url.replace(" ", ""));
                    actvDirector.setText("");
                    actvDirector.setVisibility(View.GONE);
                }
            }
        });
        director.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.SINGER_URL + director.getText().toString().trim() + "_icon" + ".jpg";
                Log.d(">>>>>>", url);
                if (viewDirectorList.size() == 0) {
                    addDirectorViewEXT(director.getText().toString(), url.replace(" ", ""));
                    director.setText("");
                    director.setVisibility(View.GONE);
                }

            }
        });
        actor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = Constants.SINGER_URL + actor.getText().toString().trim() + ".jpg";
                Log.d(">>>>>>", url);
                addActorViewEXT(actor.getText().toString(), url.replace(" ", ""));
                actor.setText("");
            }
        });

    }

    private void initializeData() {
        musicRestAdapter = new MusicRestAdapter();
        String[] actors = StringUtility.getActorList();
        String[] directors = StringUtility.getDirectorList();
        String[] dir = StringUtility.getDirectorList();
        calendar = Calendar.getInstance();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_text_layout, actors);
        actvActor.setAdapter(adapter);
        actvDirector.setAdapter(adapter);
        ArrayAdapter adapter3 = new ArrayAdapter<String>(this, R.layout.simple_text_layout, dir);
        director.setAdapter(adapter3);
        actor.setAdapter(adapter);
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
        actvActor = (AutoCompleteTextView) findViewById(R.id.actv_actor);
        actvDirector = (AutoCompleteTextView) findViewById(R.id.actv_director);
        director = (AutoCompleteTextView) findViewById(R.id.actv_directorrrr);
        actor = (AutoCompleteTextView) findViewById(R.id.actv_actorrr);
        tvSingerCount = (TextView) findViewById(R.id.tv_actors_count);
        tvComposerCount = (TextView) findViewById(R.id.tv_director_count);
        tvDirectorCount = (TextView) findViewById(R.id.tv_director_counttt);
        tvActorCount = (TextView) findViewById(R.id.tv_actor_counttt);
        rvMusic = (RecyclerView) findViewById(R.id.rv_moveList);
        btStartDate = (Button) findViewById(R.id.bt_start_date);
        btEndDate = (Button) findViewById(R.id.bt_end_date);

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
        }
        return super.onOptionsItemSelected(item);
    }

    public void filterConditionClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.rl_cast:
                Toast.makeText(getApplicationContext(), "filterConditionClick", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewSingerList.size(); i++) {
                    ImageView deleteButton = (ImageView) viewSingerList.get(i).findViewById(R.id.iv_delete);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted = viewSingerList.get(i);
                        llSingerParentLayout.removeView(viewSingerList.get(i));
                    }
                }
                viewSingerList.remove(viewTobeDeleted);
                if (viewSingerList.size() == 0) {
                    tvSingerCount.setVisibility(View.GONE);
                } else {
                    tvSingerCount.setVisibility(View.VISIBLE);
                }
                tvSingerCount.setText("" + viewSingerList.size());
                break;
            case R.id.iv_delete_director:
                View viewTobeDeleted2 = null;
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
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
                actvDirector.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_delete_directorrr:
                View viewTobeDeleted3 = null;
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
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
                director.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_delete_actorrrr:
                View viewTobeDeleted4 = null;
                Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
                for (int i = 0; i < viewActor.size(); i++) {
                    ImageView deleteButton = (ImageView) viewActor.get(i).findViewById(R.id.iv_delete_actorrrr);
                    if (deleteButton.equals(view)) {
                        viewTobeDeleted4 = viewActor.get(i);
                        llActorParentLayout.removeView(viewActor.get(i));
                    }
                }
                viewSingerList.remove(viewTobeDeleted4);
                if (viewSingerList.size() == 0) {
                    tvActorCount.setVisibility(View.GONE);
                } else {
                    tvActorCount.setVisibility(View.VISIBLE);
                }
                tvActorCount.setText("" + viewDirectorList.size());
                director.setVisibility(View.VISIBLE);
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


    private void addActorView(String actorNamee, String imageUrl) {
        final View view = LayoutInflater.from(this).inflate(R.layout.block_actor, null);
        TextView actorName = (TextView) view.findViewById(R.id.tv_actor);
        ImageView actorImage = (ImageView) view.findViewById(R.id.iv_actor);
        viewSingerList.add(view);
        llSingerParentLayout.addView(view);

        if (viewSingerList.size() == 0) {
            tvSingerCount.setVisibility(View.GONE);
        } else {
            tvSingerCount.setVisibility(View.VISIBLE);
        }
        tvSingerCount.setText("" + viewSingerList.size());
        actorName.setText(actorNamee);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, actorImage, options);
    }

    private void addDirectorView(String DirectorNamee, String imageUrl) {
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
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, actorImage, options);
    }


    private void addDirectorViewEXT(String DirectorNamee, String imageUrl) {
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
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, actorImage, options);
    }

    private void addActorViewEXT(String DirectorNamee, String imageUrl) {
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
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage(imageUrl, actorImage, options);
    }

    private void searchMovies() {
        String singerList = null;
        String composerList = null;
        String directorList = null;
        String startTime;
        String endTime;

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


        startTime = btStartDate.getText().toString();
        endTime = btEndDate.getText().toString();
        String query = singerList + "&" + directorList;
        Toast.makeText(getApplicationContext(), "query is\n" + query, Toast.LENGTH_LONG).show();
        LogMessage.printLog(TAG, query);

        Call<List<Music>> call = musicRestAdapter.getMusicDetails(singerList, null, directorList, startTime, endTime);
        call.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                List<Music> musics = response.body();
                Log.d(">>>>>>", musics.toString());
                if (musicAdapter == null) {
                    musicAdapter = new RvMusicAdapter(getApplicationContext(), musics);
                    rvMusic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvMusic.setAdapter(musicAdapter);
                } else {
                    musicAdapter.setNotifyChange(musics);
                }


            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {

                String aa = t.getMessage();
                Log.d(">>>>>>", aa);
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
}

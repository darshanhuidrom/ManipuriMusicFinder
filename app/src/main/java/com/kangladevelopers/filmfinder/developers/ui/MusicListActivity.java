package com.kangladevelopers.filmfinder.developers.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.kangladevelopers.filmfinder.Activity.EditMusicDetails;
import com.kangladevelopers.filmfinder.Activity.MusicDetailActivity;
import com.kangladevelopers.filmfinder.Adapter.RvMusicAdapter;
import com.kangladevelopers.filmfinder.MyApplication;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.ProgressBarConfig;
import com.kangladevelopers.filmfinder.pogo.Music;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicListActivity extends AppCompatActivity {

    private String data;
    private RecyclerView rvMusic;
    RvMusicAdapter musicAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        rvMusic = (RecyclerView) findViewById(R.id.rv_moveList);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        data = getIntent().getStringExtra("data");
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        Call<List<Music>> call = MyApplication.getResAdapter().getIncompleteData(data);
        ProgressBarConfig.showProgressBar(MusicListActivity.this, null);
        call.enqueue(new Callback<List<Music>>() {
            @Override
            public void onResponse(Call<List<Music>> call, Response<List<Music>> response) {
                List<Music> musics = response.body();
                if (musicAdapter == null) {
                    if (musics.size() == 0) {
                        ProgressBarConfig.dismissProgressBar();
                        return;
                    }
                    musicAdapter = new RvMusicAdapter(getApplicationContext(), musics);
                    musicAdapter.setRvAdapterClickLIstener(new RvMusicAdapter.RvAdapterClickListener() {
                        @Override
                        public void onItemClick(int i, View v) {
                            Intent intent = new Intent(MusicListActivity.this, EditMusicDetails.class);
                            intent.putExtra("music", musicAdapter.getData().get(i));
                            intent.putExtra("is_from_developer", true);
                            startActivity(intent);
                        }
                    });
                    rvMusic.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvMusic.setAdapter(musicAdapter);
                } else {
                    musicAdapter.setNotifyChange(musics);
                }

                ProgressBarConfig.dismissProgressBar();
            }

            @Override
            public void onFailure(Call<List<Music>> call, Throwable t) {
                ProgressBarConfig.dismissProgressBar();

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

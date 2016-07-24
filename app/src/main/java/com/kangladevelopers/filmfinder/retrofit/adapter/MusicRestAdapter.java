package com.kangladevelopers.filmfinder.retrofit.adapter;

import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.BioData;
import com.kangladevelopers.filmfinder.pogo.CorrectionModel;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.pogo.SimpleResponse;
import com.kangladevelopers.filmfinder.retrofit.interfaces.MusicAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicRestAdapter {
    protected final String TAG = getClass().getSimpleName();
    protected Retrofit retrofit;
    private MusicAPI musicApi;

    public MusicRestAdapter() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        musicApi = retrofit.create(MusicAPI.class);
    }



    public Call<List<Music>> getMusicDetails(String singer,String composer,String director,String actor,String fixSinger,String startTime,String endTime) {
        return musicApi.getMusicDetails(singer, composer, director,actor,fixSinger,startTime,endTime);
    }

    public  Call<SimpleResponse> putMusicDetails(Music music){
        return musicApi.PutMusicDetails(music);
    }

    public  Call<SimpleResponse> postCorrection(CorrectionModel correctionModel){
        return musicApi.postCorrection(correctionModel);
    }

    public  Call<BioData> getBioData(String name){
        return musicApi.getBioData(name);
    }

    public  Call<Music> getMusicDetails(String id){
        return musicApi.getMusicDetails(id);
    }


    public Call<List<Music>> getIncompleteData(String id){
        return musicApi.getIncompleteData(id);
    }



}

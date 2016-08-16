package com.kangladevelopers.filmfinder.retrofit.adapter;

import com.kangladevelopers.filmfinder.Utility.Constants;
import com.kangladevelopers.filmfinder.pogo.Person;
import com.kangladevelopers.filmfinder.pogo.Person1;
import com.kangladevelopers.filmfinder.pogo.VersionInfo;
import com.kangladevelopers.filmfinder.retrofit.interfaces.MusicAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HUIDROM on 8/13/2016.
 */
public class TestResAdapter {

    protected final String TAG = getClass().getSimpleName();
    protected Retrofit retrofit;
    private MusicAPI musicApi;

    public TestResAdapter() {
        retrofit = new Retrofit.Builder().baseUrl(Constants.VERSION_URL).addConverterFactory(GsonConverterFactory.create()).build();
        musicApi = retrofit.create(MusicAPI.class);
    }

    public Call<VersionInfo> getVersionInfo(){
        return musicApi.getVersionInfo();
    }
    public Call<String> getTestFromJsonGenerator1(String name){
        return musicApi.getTestFromJsonGenerator1(name);
    }
}

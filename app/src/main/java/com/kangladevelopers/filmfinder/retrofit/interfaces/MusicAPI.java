package com.kangladevelopers.filmfinder.retrofit.interfaces;

import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.pogo.Music;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
// https://futurestud.io/blog/retrofit-multiple-query-parameters-of-same-name


/**
 * Created by HUIDROM on 6/12/2016.
 */
public interface MusicAPI {

    @GET("songs")
    Call<List<Music>> getMusicDetails(
            @Query("singer") String singer,
            @Query("composer") String composer,
            @Query(("director")) String director,
            @Query("start_time") String startTime,
            @Query(("end_time")) String endTime
    );


}

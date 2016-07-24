package com.kangladevelopers.filmfinder.retrofit.interfaces;

import com.kangladevelopers.filmfinder.DataModel.MovieInfo2;
import com.kangladevelopers.filmfinder.pogo.BioData;
import com.kangladevelopers.filmfinder.pogo.CorrectionModel;
import com.kangladevelopers.filmfinder.pogo.Movie;
import com.kangladevelopers.filmfinder.pogo.Music;
import com.kangladevelopers.filmfinder.pogo.SimpleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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
            @Query(("actor")) String actor,
            @Query(("fix_singer")) String fixSinger,
            @Query("start_time") String startTime,
            @Query(("end_time")) String endTime
    );

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("dev/dataChanger")
    Call<SimpleResponse> PutMusicDetails(@Body Music music);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("dev/correction")
    Call<SimpleResponse> postCorrection(@Body CorrectionModel correctionModel);

    @GET("data/bio_data/{name}")
    Call<BioData> getBioData(@Path("name") String name);


}

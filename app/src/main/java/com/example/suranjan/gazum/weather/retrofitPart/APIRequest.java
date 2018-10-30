package com.example.suranjan.gazum.weather.retrofitPart;

import com.example.suranjan.gazum.weather.retrofitPart.model.WeatherData;
import com.example.suranjan.gazum.youtube.retrofitPart.model.YoutubeSearchData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequest {
    @GET("{latitude},{longitude}")
    Call<WeatherData> getData(
            @Path("latitude") String latitude,
            @Path("longitude") String longitude,
            @Query("exclude") String exclude,
            @Query("units") String units
    );

    @GET()
    Call<YoutubeSearchData> getYoutubeSearchResults(
            @Query("part") String part,
            @Query("maxResults") String maxResults,
            @Query("q") String q,
            @Query("type") String type,
            @Query("key") String key
    );
}

package com.example.suranjan.gazum.weather.retrofitPart;

import com.example.suranjan.gazum.weather.retrofitPart.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
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
}

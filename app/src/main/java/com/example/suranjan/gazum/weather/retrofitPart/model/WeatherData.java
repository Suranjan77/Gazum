package com.example.suranjan.gazum.weather.retrofitPart.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class WeatherData {

    @SerializedName("longitude")
    @Expose
    private double longitude;

    @SerializedName("latitude")
    @Expose
    private double latitude;

    @SerializedName("timezone")
    @Expose
    private String timeZone;

    @SerializedName("currently")
    @Expose
    private Current current;

    @SerializedName("offset")
    @Expose
    private float offset;

}

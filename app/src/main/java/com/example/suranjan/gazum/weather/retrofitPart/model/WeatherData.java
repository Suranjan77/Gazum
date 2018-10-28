package com.example.suranjan.gazum.weather.retrofitPart.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public WeatherData(double longitude, double latitude, String timeZone, Current current, float offset) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeZone = timeZone;
        this.current = current;
        this.offset = offset;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", current=" + current +
                '}';
    }
}

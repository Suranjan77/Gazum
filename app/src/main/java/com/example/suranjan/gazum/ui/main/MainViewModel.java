package com.example.suranjan.gazum.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.suranjan.gazum.utilities.WeatherUtil;
import com.example.suranjan.gazum.utilities.YoutubeApiKey;
import com.example.suranjan.gazum.weather.retrofitPart.APIRequest;
import com.example.suranjan.gazum.weather.retrofitPart.model.WeatherData;
import com.example.suranjan.gazum.youtube.retrofitPart.model.YoutubeSearchData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";

    private String latitude;
    private String longitude;
    private String searchQuery;
    private int maxResults;
    private APIRequest apiRequest;

    //It is fetched asyncronously using retrofit
    private MutableLiveData<WeatherData> weatherData;
    private MutableLiveData<YoutubeSearchData> youtubeSearchData;

    public void setLatitudeLongitude(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LiveData<WeatherData> getWeatherData() {
        if (weatherData == null) {
            weatherData = new MutableLiveData<>();
            loadWeatherData();
        }
        return weatherData;
    }

    public void setYoutubeSearchParams(String searchQuery, int maxResults) {
        this.searchQuery = searchQuery;
        this.maxResults = maxResults;
    }

    public LiveData<YoutubeSearchData> getYoutubeSearchData() {
        if (youtubeSearchData == null) {
            youtubeSearchData = new MutableLiveData<>();
            loadYoutubeSearchData();
        }
        return youtubeSearchData;
    }

    //Load data asynchronously using retrofit
    private void loadYoutubeSearchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(YoutubeApiKey.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(apiRequest == null) {
            apiRequest = retrofit.create(APIRequest.class);
        }

        Call<YoutubeSearchData> call = apiRequest.getYoutubeSearchResults(
                "snippet",
                String.valueOf(maxResults),
                searchQuery,
                "video",
                YoutubeApiKey.KEY
        );

        call.enqueue(new Callback<YoutubeSearchData>() {
            @Override
            public void onResponse(Call<YoutubeSearchData> call, Response<YoutubeSearchData> response) {
                youtubeSearchData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<YoutubeSearchData> call, Throwable t) {
                Log.e(TAG, "onFailure: Cannot fetch youtube data");
            }
        });
    }

    private void loadWeatherData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherUtil.BASE_URL + WeatherUtil.KEY +"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        if(apiRequest == null) {
            apiRequest = retrofit.create(APIRequest.class);
        }

        Call<WeatherData> call = apiRequest.getData(
                latitude,
                longitude,
                "hourly,daily,flags",
                "auto"
        );
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                weatherData.setValue(response.body());
                Log.i(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.i(TAG, "onFailure: Failed to retrieve weather");
            }
        });
    }
}

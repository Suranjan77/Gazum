package com.example.suranjan.gazum.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.suranjan.gazum.utilities.WeatherUtil;
import com.example.suranjan.gazum.weather.retrofitPart.APIRequest;
import com.example.suranjan.gazum.weather.retrofitPart.model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel extends ViewModel{

    private static final String TAG = "MainViewModel";

    private String latitude;
    private String longitude;

    //It is fetched asyncronously using retrofit
    private MutableLiveData<WeatherData> weatherData;

    public void setLatitudeLongitude(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LiveData<WeatherData> getWeatherData(){
        if(weatherData == null){
            weatherData = new MutableLiveData<>();
            loadData();
        }
        return weatherData;
    }

    //Load data asynchronously using retrofit
    private void loadData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherUtil.BASE_URL+WeatherUtil.KEY+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIRequest APIRequest = retrofit.create(APIRequest.class);

        Call<WeatherData> call = APIRequest.getData(
                latitude,
                longitude,
                "hourly,daily,flags",
                "auto"
        );
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                weatherData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }
}

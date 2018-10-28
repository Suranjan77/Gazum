package com.example.suranjan.gazum.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.suranjan.gazum.R;
import com.example.suranjan.gazum.weather.retrofitPart.model.WeatherData;
import com.github.pwittchen.weathericonview.WeatherIconView;

import java.util.HashMap;
import java.util.Map;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private MainViewModel mViewModel;
    private Map<String, Integer> weatherIcon;

    private final int[] iconResources = {
            R.string.wi_day_sunny,
            R.string.wi_night_clear,
            R.string.wi_day_cloudy,
            R.string.wi_night_alt_cloudy,
            R.string.wi_cloudy,
            R.string.wi_rain,
            R.string.wi_sleet,
            R.string.wi_snow,
            R.string.wi_windy,
            R.string.wi_fog
    };

    private String iconName;
    private String longitude;
    private String latitude;
    private TextView temperatureView;
    private TextView cityCountryView;
    private String descriptionText;
    private TextView descriptionView;
    private WeatherIconView iconView;
    private TextView degreeCelcius;
    private ProgressBar progressBar;

    public static MainFragment newInstance(String longitude, String latitude) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("longitude", longitude);
        bundle.putString("latitude", latitude);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(getArguments() != null){
            longitude = getArguments().getString("longitude");
            latitude = getArguments().getString("latitude");
        }

        weatherIcon = new HashMap<>();
        Resources res = getResources();
        String[] fakeIconName = res.getStringArray(R.array.iconNamesArray);
        for(int i=0; i<fakeIconName.length; i++){
            weatherIcon.put(fakeIconName[i], iconResources[i]);
        }

        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.setLatitudeLongitude(latitude, longitude);

        temperatureView = getView().findViewById(R.id.view_temperature);
        cityCountryView = getView().findViewById(R.id.view_location);
        descriptionView = getView().findViewById(R.id.view_weather_description);
        iconView = getView().findViewById(R.id.view_weather_icon);
        degreeCelcius = getView().findViewById(R.id.degree_celcius);
        progressBar = getView().findViewById(R.id.weatherProgress);
        mViewModel.getWeatherData().observe(this, new Observer<WeatherData>() {
            @Override
            public void onChanged(@Nullable WeatherData weatherData) {
                progressBar.setVisibility(View.GONE);
                temperatureView.setText(String.valueOf((int)weatherData.getCurrent().getTemperature()));
                cityCountryView.setText(String.valueOf(weatherData.getTimeZone()));
                setDescription();
                degreeCelcius.setText("o");
                iconName = weatherData.getCurrent().getIcon();
                iconView.setIconResource(getString(weatherIcon.get(iconName)));
            }
        });
    }

    private void setDescription(){
        descriptionText = "Its rainy today! Let's watch some movies";
        descriptionView.setText(descriptionText);
    }
}

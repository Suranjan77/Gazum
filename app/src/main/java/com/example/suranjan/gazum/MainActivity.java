package com.example.suranjan.gazum;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.suranjan.gazum.ui.main.MainFragment;
import com.example.suranjan.gazum.ui.main.VideoListFragment;
import com.example.suranjan.gazum.ui.player.YoutubePlayerFragment;
import com.example.suranjan.gazum.ui.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements VideoListFragment.OnFragmentInteractionListener {
    public static final String TAG = "MainActivity";

    boolean permissionStatus = false;
    private LocationManager locationManager;
    private Location location;
    private double longitude;
    private double latitude;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        checkLocationPermission();

        final Intent searchIntent = new Intent(this, SearchActivity.class);

        floatingActionButton = findViewById(R.id.btn_floating_search);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(searchIntent);
            }
        });

        if (permissionStatus) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.weather_container,
                        MainFragment.newInstance(String.valueOf(longitude), String.valueOf(latitude))
                )
                .replace(
                        R.id.video_list_container,
                        VideoListFragment.newInstance("English Songs")
                ).commit();
    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET
                        }, 10);
            }
        } else {
            permissionStatus = true;
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionStatus = true;
                } else {
                    permissionStatus = false;
                }
                return;
            }
        }
    }

    @Override
    public void playVideo(String videoId) {
        getSupportFragmentManager().beginTransaction()
                .replace(
                        R.id.main_container,
                        YoutubePlayerFragment.newInstance(videoId)
                )
                .commit();
    }
}

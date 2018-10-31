package com.example.suranjan.gazum.ui.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

import com.example.suranjan.gazum.R;
import com.example.suranjan.gazum.ui.main.VideoListFragment;
import com.example.suranjan.gazum.ui.player.YoutubePlayerActivity;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, VideoListFragment.OnFragmentInteractionListener {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.view_search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.requestFocus();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.replacable_container_below_search, VideoListFragment.newInstance(query))
                .commit();
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void playVideo(String videoId) {
        Intent intent = new Intent(this, YoutubePlayerActivity.class);
        intent.putExtra("searchQuery", videoId);
        startActivity(intent);
    }
}

package com.example.suranjan.gazum.ui.search;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.suranjan.gazum.R;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    TextView testText;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.view_search);
        searchView.setIconifiedByDefault(false);

        testText = findViewById(R.id.test_text);

        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        testText.setText(searchView.getQuery().toString());
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}

package com.example.mytv_android_app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytv_android_app.TVListAdapter;
import com.example.mytv_android_app.databinding.ActivityTvlistBinding;
import com.example.mytv_android_app.models.TVShow;

import java.util.ArrayList;

public class TVListActivity extends AppCompatActivity {

    private ArrayList<TVShow> tvShows;

    ActivityTvlistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTvlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvShows = new ArrayList<>();
        tvShows = getIntent().getParcelableArrayListExtra("TV_LIST");

        TVListAdapter listAdapter = new TVListAdapter(TVListActivity.this, tvShows);
        binding.tvShowsList.setAdapter(listAdapter);
        binding.tvShowsList.setClickable(true);
        binding.tvShowsList.setOnItemClickListener((adapterView, view, i, l) -> {

            Intent intent = new Intent(TVListActivity.this, TVShowActivity.class);
            intent.putExtra("TV_SHOW_POSTER", tvShows.get(i).getShowPoster());
            intent.putExtra("TV_SHOW_NAME", tvShows.get(i).getName());
            intent.putExtra("TV_SHOW_CATEGORY", tvShows.get(i).getCategory());
            intent.putExtra("TV_SHOW_TRAILER", tvShows.get(i).getTrailerURL());
            startActivity(intent);
            finish();
        });
    }
}
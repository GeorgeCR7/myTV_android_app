package com.example.mytv_android_app.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytv_android_app.R;

public class TVShowActivity extends AppCompatActivity {

    ImageView tvShowImg;

    TextView txtTVShowNameLabel, txtTVShowNameValue;
    TextView txtTVShowCategLabel, txtTVShowCategValue;

    Button btnTVShowTrailer, btnFavoriteTVShow, btnBackTVShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow);

        tvShowImg = findViewById(R.id.tvShowImg);
        tvShowImg.setImageResource(getIntent().getIntExtra("TV_SHOW_POSTER", 0));

        txtTVShowNameLabel = findViewById(R.id.txtTVShowNameLabel);
        txtTVShowNameLabel.setPaintFlags(txtTVShowNameLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtTVShowCategLabel = findViewById(R.id.txtTVShowCategLabel);
        txtTVShowCategLabel.setPaintFlags(txtTVShowCategLabel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtTVShowNameValue = findViewById(R.id.txtTVShowNameValue);
        txtTVShowNameValue.setText(getIntent().getStringExtra("TV_SHOW_NAME"));
        txtTVShowCategValue = findViewById(R.id.txtTVShowCategValue);
        txtTVShowCategValue.setText(getIntent().getStringExtra("TV_SHOW_CATEGORY"));

        btnTVShowTrailer = findViewById(R.id.btnTVShowTrailer);
        btnFavoriteTVShow = findViewById(R.id.btnFavoriteTVShow);
        btnBackTVShow = findViewById(R.id.btnBackTVShow);

        btnTVShowTrailer.setOnClickListener(view -> {
            Uri trailerURL = Uri.parse(getIntent().getStringExtra("TV_SHOW_TRAILER"));
            startActivity(new Intent(Intent.ACTION_VIEW, trailerURL));
        });

        btnFavoriteTVShow.setOnClickListener(view -> {

        });

        btnBackTVShow.setOnClickListener(view -> {

        });
    }
}
package com.example.mytv_android_app.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytv_android_app.R;
import com.example.mytv_android_app.models.TVShow;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;

    // Firebase objects for reading database.
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    ArrayList<TVShow> tvShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("TV_Shows");

        tvShows = new ArrayList<>();

        button.setOnClickListener(view -> {

            /*TVShow tvShow1 = new TVShow("Breaking Bad",
                    "https://www.youtube.com/watch?v=HhesaQXLuRY",
                    1);

            TVShow tvShow2 = new TVShow("Dexter",
                    "https://www.youtube.com/watch?v=YQeUmSD1c3g",
                    2);

            tvShows.add(tvShow1);
            tvShows.add(tvShow2);

            for (TVShow tvShow : tvShows){
                reference.child(""+tvShow.getName()).setValue(tvShow);
            }*/

            /*Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();*/

        });
    }
}
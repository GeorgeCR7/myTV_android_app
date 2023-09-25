package com.example.mytv_android_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytv_android_app.R;
import com.example.mytv_android_app.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersProposalsActivity extends AppCompatActivity {

    TextView txtNoFavoriteShows;
    Button btnBackUsersProposals;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    ArrayList<User> users;

    ArrayList<String> myFavoriteShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_proposals);

        txtNoFavoriteShows = findViewById(R.id.txtNoFavoriteShows);
        txtNoFavoriteShows.setVisibility(View.INVISIBLE);

        btnBackUsersProposals = findViewById(R.id.btnBackUsersProposals);

        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        users = new ArrayList<>();
        myFavoriteShows = new ArrayList<>();

        myFavoriteShows = getIntent().getStringArrayListExtra("FAVORITE_SHOWS");

        if (myFavoriteShows.size() == 0){
            txtNoFavoriteShows.setVisibility(View.VISIBLE);
        } else {

        }

        btnBackUsersProposals.setOnClickListener(view -> {
            Intent intent = new Intent(UsersProposalsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
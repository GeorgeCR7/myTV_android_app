package com.example.mytv_android_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytv_android_app.R;
import com.example.mytv_android_app.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoriteShowsActivity extends AppCompatActivity {

    TextView txtNoFavoriteShows, txtUrFavoriteShows;

    TextView txtFavShow1, txtFavShow2, txtFavShow3;

    Button btnBackFavoriteShows;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    ArrayList<User> users;
    ArrayList<String> myFavoriteShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_shows);

        txtNoFavoriteShows = findViewById(R.id.txtNoFavoriteShows);
        txtNoFavoriteShows.setVisibility(View.INVISIBLE);

        txtUrFavoriteShows = findViewById(R.id.txtUrFavoriteShows);
        txtUrFavoriteShows.setVisibility(View.INVISIBLE);

        txtFavShow1 = findViewById(R.id.txtFavShow1);
        txtFavShow2 = findViewById(R.id.txtFavShow2);
        txtFavShow3 = findViewById(R.id.txtFavShow3);

        btnBackFavoriteShows = findViewById(R.id.btnBackFavoriteShows);

        users = new ArrayList<>();
        myFavoriteShows = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                }

                User currentUser = new User();
                for (User user: users){
                    if (user.getEmail().equals(mAuth.getCurrentUser().getEmail())) {
                        currentUser = user;
                        break;
                    }
                }

                if (!currentUser.getFavShow1().equals("")){
                    myFavoriteShows.add(currentUser.getFavShow1());
                }
                if (!currentUser.getFavShow2().equals("")){
                    myFavoriteShows.add(currentUser.getFavShow2());
                }
                if (!currentUser.getFavShow3().equals("")){
                    myFavoriteShows.add(currentUser.getFavShow3());
                }

                initUIActivity(myFavoriteShows);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        btnBackFavoriteShows.setOnClickListener(view -> {
            Intent intent = new Intent(FavoriteShowsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initUIActivity(ArrayList<String> shows){

        if (shows.size() == 0){
            txtNoFavoriteShows.setVisibility(View.VISIBLE);
        } else {
            txtUrFavoriteShows.setVisibility(View.VISIBLE);
            txtFavShow1.setText(shows.get(0));
            txtFavShow2.setText(shows.get(1));
            txtFavShow3.setText(shows.get(2));
        }
    }
}
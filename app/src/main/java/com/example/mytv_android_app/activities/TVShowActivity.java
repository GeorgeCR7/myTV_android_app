package com.example.mytv_android_app.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;

public class TVShowActivity extends AppCompatActivity {

    ImageView tvShowImg;

    TextView txtTVShowNameLabel, txtTVShowNameValue;
    TextView txtTVShowCategLabel, txtTVShowCategValue;

    Button btnTVShowTrailer, btnFavoriteTVShow, btnBackTVShow;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvshow);

        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        users = new ArrayList<>();

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
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        User user = dataSnapshot.getValue(User.class);
                        users.add(user);
                    }

                    // Find current user.
                    User currentUser = new User();
                    for (User user: users){
                        if(user.getEmail().equals(mAuth.getCurrentUser().getEmail())) {
                            currentUser = user;
                            break;
                        }
                    }

                    // Add this TV show to favorite for this current User.
                    addFavoriteShow(currentUser, getIntent().getStringExtra("TV_SHOW_NAME"));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        });

        btnBackTVShow.setOnClickListener(view -> {
            Intent intent = new Intent(TVShowActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void addFavoriteShow(User currentUser, String showName) {

        HashMap hashMap = new HashMap();

        if (currentUser.getFavShow1().isEmpty()) {
            hashMap.put("favShow1", showName);
        } else if (!currentUser.getFavShow1().isEmpty()
                && currentUser.getFavShow2().isEmpty()) {
            hashMap.put("favShow2", showName);
        } else if (!currentUser.getFavShow1().isEmpty()
                && !currentUser.getFavShow2().isEmpty()
                && currentUser.getFavShow3().isEmpty()) {
            hashMap.put("favShow3", showName);
        }

        reference.child(mAuth.getCurrentUser().getEmail().replace(".","")).
                updateChildren(hashMap)
                .addOnSuccessListener(o -> Toast.makeText(TVShowActivity.this,
                        R.string.favorite_added, Toast.LENGTH_SHORT).show());
    }
}
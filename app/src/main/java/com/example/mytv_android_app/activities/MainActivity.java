package com.example.mytv_android_app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytv_android_app.R;
import com.example.mytv_android_app.models.TVShow;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button button;
    /////////////////////////////////////////
    TextView txtAppTitle, txtDate;

    Button btnAllShows, btnAppProposals, btnUsersProposals;
    Button btnProfile, btnLogOut;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    ArrayList<TVShow> tvShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        //////////////////////////////////////////////
        txtAppTitle = findViewById(R.id.txtAppTitle);
        //colorMainAppTitle(txtAppTitle.getText().toString());

        txtDate = findViewById(R.id.txtDate);
        txtDate.setText(setCurrentDate());

        btnAllShows= findViewById(R.id.btnAllShows);
        btnAppProposals = findViewById(R.id.btnAppProposals);
        btnUsersProposals = findViewById(R.id.btnUsersProposals);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogOut = findViewById(R.id.btnLogOut);

        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("TV_Shows");

        tvShows = new ArrayList<>();

        button.setOnClickListener(view -> {

            TVShow tvShow1 = new TVShow("Breaking Bad",
                    getResources().getString(R.string.action_cat),
                    "https://www.youtube.com/watch?v=HhesaQXLuRY",
                    R.drawable.breaking_bad);

            TVShow tvShow2 = new TVShow("Dexter",
                    getResources().getString(R.string.crime_cat),
                    "https://www.youtube.com/watch?v=YQeUmSD1c3g",
                    R.drawable.dexter);

            TVShow tvShow3 = new TVShow("The Walking Dead",
                    getResources().getString(R.string.fantasy_cat),
                    "https://www.youtube.com/watch?v=sfAc2U20uyg",
                    R.drawable.the_walking_dead);

            TVShow tvShow4 = new TVShow("Prison Break",
                    getResources().getString(R.string.action_cat),
                    "https://www.youtube.com/watch?v=AL9zLctDJaU",
                    R.drawable.prison_break);

            tvShows.add(tvShow1);
            tvShows.add(tvShow2);
            tvShows.add(tvShow3);
            tvShows.add(tvShow4);

            for (TVShow tvShow : tvShows){
                reference.child(""+tvShow.getName()).setValue(tvShow);
            }

            /*Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();*/

        });
        /////////////////////////////////////////////////

        btnAllShows.setOnClickListener(view -> {

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        TVShow tvShow = dataSnapshot.getValue(TVShow.class);
                        tvShows.add(tvShow);
                    }
                    Intent intent = new Intent(MainActivity.this, TVListActivity.class);
                    intent.putParcelableArrayListExtra("TV_LIST", tvShows);
                    startActivity(intent);
                    finish();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });

        });

        btnAppProposals.setOnClickListener(view -> {

        });

        btnUsersProposals.setOnClickListener(view -> {

        });

        btnProfile.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });

        btnLogOut.setOnClickListener(view -> {
            mAuth.signOut();
            Toast.makeText(MainActivity.this,
                    R.string.log_out_main,
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void colorMainAppTitle (String strTxtAppTitle) {
        SpannableString ss = new SpannableString(strTxtAppTitle);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.GREEN);
        ss.setSpan(fcsRed, 0,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsBlue, 5,9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsGreen, 10,12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtAppTitle.setText(ss);
    }

    private String setCurrentDate(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        String dayName = null, day, month;

        if(cal.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + cal.get(Calendar.DAY_OF_MONTH)+".";
        } else {
            day = ""+cal.get(Calendar.DAY_OF_MONTH)+".";
        }

        if((cal.get(Calendar.MONTH)+1) < 10) {
            month = "0" + (cal.get(Calendar.MONTH)+1)+".";
        } else {
            month = ""+(cal.get(Calendar.MONTH)+1)+".";
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            DayOfWeek dayOfWeek = today.getDayOfWeek();
            dayName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        }

        if (dayName.equals("Sunday")) {
            dayName = getResources().getString(R.string.sunday);
        } else if (dayName.equals("Monday")) {
            dayName = getResources().getString(R.string.monday);
        } else if (dayName.equals("Tuesday")) {
            dayName = getResources().getString(R.string.tuesday);
        } else if (dayName.equals("Wednesday")) {
            dayName = getResources().getString(R.string.wednesday);
        } else if (dayName.equals("Thursday")) {
            dayName = getResources().getString(R.string.thursday);
        } else if (dayName.equals("Friday")) {
            dayName = getResources().getString(R.string.friday);
        } else if (dayName.equals("Saturday")) {
            dayName = getResources().getString(R.string.saturday);
        }

        return dayName + ", " +day + month + cal.get(Calendar.YEAR);
    }
}
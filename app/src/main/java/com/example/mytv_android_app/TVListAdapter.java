package com.example.mytv_android_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mytv_android_app.models.TVShow;

import java.util.ArrayList;

public class TVListAdapter extends ArrayAdapter<TVShow> {

    public TVListAdapter(Context context, ArrayList<TVShow> tvShows){
        super(context, R.layout.list_item, tvShows);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TVShow tvShow = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView tvShowPoster = convertView.findViewById(R.id.tvShowPoster);
        TextView tvShowName = convertView.findViewById(R.id.tvShowName);

        tvShowPoster.setImageResource(tvShow.getShowPoster());
        tvShowName.setText(tvShow.getName());

        return convertView;
    }
}

package com.example.mytv_android_app.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TVShow implements Parcelable {

    private String name, trailerURL, category;
    private int showPoster;

    public TVShow() {}

    public TVShow(String name, String category, String trailerURL, int showPoster) {
        this.name = name;
        this.category = category;
        this.trailerURL = trailerURL;
        this.showPoster = showPoster;
    }

    protected TVShow(Parcel in) {
        name = in.readString();
        category = in.readString();
        trailerURL = in.readString();
        showPoster = in.readInt();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public int getShowPoster() {
        return showPoster;
    }

    public void setShowPoster(int showPoster) {
        this.showPoster = showPoster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(category);
        parcel.writeString(trailerURL);
        parcel.writeInt(showPoster);
    }
}

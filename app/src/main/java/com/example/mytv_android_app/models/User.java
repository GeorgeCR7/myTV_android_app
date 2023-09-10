package com.example.mytv_android_app.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    private String email, name, country, dateCreated, age;
    private String favCategory, favShow1, favShow2, favShow3;

    public User() {}

    public User(String email, String name, String country, String dateCreated, String age, String favCategory, String favShow1, String favShow2, String favShow3) {
        this.email = email;
        this.name = name;
        this.country = country;
        this.dateCreated = dateCreated;
        this.age = age;
        this.favCategory = favCategory;
        this.favShow1 = favShow1;
        this.favShow2 = favShow2;
        this.favShow3 = favShow3;
    }

    protected User(Parcel in) {
        email = in.readString();
        name = in.readString();
        country = in.readString();
        dateCreated = in.readString();
        age = in.readString();
        favCategory = in.readString();
        favShow1 = in.readString();
        favShow2 = in.readString();
        favShow3 = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavCategory() {
        return favCategory;
    }

    public void setFavCategory(String favCategory) {
        this.favCategory = favCategory;
    }

    public String getFavShow1() {
        return favShow1;
    }

    public void setFavShow1(String favShow1) {
        this.favShow1 = favShow1;
    }

    public String getFavShow2() {
        return favShow2;
    }

    public void setFavShow2(String favShow2) {
        this.favShow2 = favShow2;
    }

    public String getFavShow3() {
        return favShow3;
    }

    public void setFavShow3(String favShow3) {
        this.favShow3 = favShow3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(name);
        parcel.writeString(country);
        parcel.writeString(dateCreated);
        parcel.writeString(age);
        parcel.writeString(favCategory);
        parcel.writeString(favShow1);
        parcel.writeString(favShow2);
        parcel.writeString(favShow3);
    }
}

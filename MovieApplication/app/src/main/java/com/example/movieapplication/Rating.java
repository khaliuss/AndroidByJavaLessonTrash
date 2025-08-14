package com.example.movieapplication;

import com.google.gson.annotations.SerializedName;

public class Rating {

    @SerializedName("kp")
    private String ratingKp;

    public Rating(String kp) {
        this.ratingKp = kp;
    }

    public String getRatingKp() {
        return ratingKp;
    }
}

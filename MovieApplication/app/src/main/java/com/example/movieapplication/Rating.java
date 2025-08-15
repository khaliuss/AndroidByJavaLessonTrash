package com.example.movieapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {

    @SerializedName("kp")
    private double ratingKp;

    public Rating(double kp) {
        this.ratingKp = kp;
    }

    public double getRatingKp() {
        return ratingKp;
    }
}

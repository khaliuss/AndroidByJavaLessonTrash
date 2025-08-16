package com.example.movieapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerRespons {

    @SerializedName("video")
    TrailerList trailerList;

    public TrailerRespons(TrailerList trailerList) {
        this.trailerList = trailerList;
    }

    public TrailerList getTrailerList() {
        return trailerList;
    }

    @Override
    public String toString() {
        return "TrailerRespons{" +
                "trailerList=" + trailerList +
                '}';
    }
}

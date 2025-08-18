package com.example.movieapplication;

import com.google.gson.annotations.SerializedName;

public class TrailerResponse {

    @SerializedName("videos")
    private TrailerList trailerList;

    public TrailerResponse(TrailerList trailerList) {
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

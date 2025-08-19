package com.example.movieapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class FavoriteViewModel extends AndroidViewModel {

    private final MovieDao movieDao;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    LiveData<List<Movie>> getMovies(){
        return movieDao.getMovies();
    }


}

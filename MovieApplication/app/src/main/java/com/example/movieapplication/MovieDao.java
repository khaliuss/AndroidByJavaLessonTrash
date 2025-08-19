package com.example.movieapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {


    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getMovies();

    @Query("SELECT * FROM movies WHERE id =:movieId")
    LiveData<Movie> favoriteMovie(int movieId);



    @Insert
    Completable addMovie(Movie movie);


    @Query("DELETE FROM movies WHERE id = :movieId")
    Completable deleteMovie(int movieId);


}

package com.example.movieapplication;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class},version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;
    private final static String DB_NAME = "MovieDB";

    public static MovieDatabase getInstance(Application application) {
        if (instance == null){
            instance = Room.databaseBuilder(
                    application,
                    MovieDatabase.class,
                    DB_NAME
            ).build();
        }

        return instance;
    }

    abstract MovieDao movieDao();
}

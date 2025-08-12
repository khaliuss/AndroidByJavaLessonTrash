package com.example.todolistap;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NotesDataBase extends RoomDatabase {

    private static NotesDataBase instance = null;
    private static final String DB_NAME = "notes.db";

    public static NotesDataBase getInstance(Application application){
        if (instance == null){
            instance = Room.databaseBuilder(
                    application,
                    NotesDataBase.class,
                    DB_NAME
                    ).build();
        }
        return instance;
    }

    public abstract NotesDao notesDao();

}

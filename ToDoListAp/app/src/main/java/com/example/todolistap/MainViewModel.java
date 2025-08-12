package com.example.todolistap;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel{

    NotesDataBase notesDataBase;
    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDataBase = NotesDataBase.getInstance(application);
    }

    public LiveData<List<Note>> getNotes(){
        return notesDataBase.notesDao().getNotes();
    }

    public void remove(Note note){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDataBase.notesDao().remove(note.getId());
            }
        });
    }
}

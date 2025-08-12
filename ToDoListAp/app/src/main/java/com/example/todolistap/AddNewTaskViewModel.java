package com.example.todolistap;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AddNewTaskViewModel extends AndroidViewModel {

    private NotesDao notesDao;
    private MutableLiveData<Boolean> shouldClose = new MutableLiveData<>();

    public LiveData<Boolean> getShouldClose() {
        return shouldClose;
    }

    public AddNewTaskViewModel(@NonNull Application application) {
        super(application);
        notesDao = NotesDataBase.getInstance(application).notesDao();
    }

    public void add(Note note){
        Thread tread = new Thread(new Runnable() {
            @Override
            public void run() {
                notesDao.add(note);
                shouldClose.postValue(true);
            }
        });
        tread.start();

    }
}

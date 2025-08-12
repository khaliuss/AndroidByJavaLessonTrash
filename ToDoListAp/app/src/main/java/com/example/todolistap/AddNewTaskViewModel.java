package com.example.todolistap;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.security.auth.Destroyable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddNewTaskViewModel extends AndroidViewModel {

    private NotesDao notesDao;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MutableLiveData<Boolean> shouldClose = new MutableLiveData<>();

    public LiveData<Boolean> getShouldClose() {
        return shouldClose;
    }

    public AddNewTaskViewModel(@NonNull Application application) {
        super(application);
        notesDao = NotesDataBase.getInstance(application).notesDao();
    }


    public void saveNote(Note note) {
        Disposable disposable = addRx(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        shouldClose.setValue(true);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public Completable addRx(Note note){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                notesDao.add(note);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

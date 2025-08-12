package com.example.todolistap;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private NotesDataBase notesDataBase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Note>> notes = new MutableLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
        notesDataBase = NotesDataBase.getInstance(application);
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void refreshList() {
        Disposable disposable =
                getNotesRX()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Note>>() {
                            @Override
                            public void accept(List<Note> notesFromDb) throws Throwable {
                                notes.setValue(notesFromDb);
                            }
                        });
        compositeDisposable.add(disposable);
    }

    public Single<List<Note>> getNotesRX() {
        return Single.fromCallable(new Callable<List<Note>>() {
            @Override
            public List<Note> call() throws Exception {
                return notesDataBase.notesDao().getNotes();
            }
        });
    }


    public void remove(Note note) {
        Disposable disposable = removeRx(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        refreshList();
                    }
                });
        compositeDisposable.add(disposable);
    }

    public Completable removeRx(Note note){
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                notesDataBase.notesDao().remove(note.getId());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

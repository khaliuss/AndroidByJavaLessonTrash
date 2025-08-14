package com.example.movieapplication;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Movie>> mldMovies = new MutableLiveData();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData();

    private int page = 1;
    private final CompositeDisposable compositeDisposable= new CompositeDisposable();;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    LiveData<List<Movie>> getMovies(){
        return mldMovies;
    }

    LiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    void loadMovies(){
        Disposable disposable = ApiFactory.apiservice.loadMovies(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        isLoading.setValue(true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        isLoading.setValue(false);
                    }
                })
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse movieResponse) throws Throwable {
                        page++;
                        mldMovies.setValue(movieResponse.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("MainActivity",throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }





    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

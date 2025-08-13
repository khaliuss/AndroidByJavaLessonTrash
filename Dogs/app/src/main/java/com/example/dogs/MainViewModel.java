package com.example.dogs;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private final String BASE_URL = "https://dog.ceo/api/breeds/image/";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<DogImage> mutableLiveData = new MutableLiveData();
    private MutableLiveData<Boolean> progressBarReaction = new MutableLiveData();
    private MutableLiveData<Boolean> mutableError = new MutableLiveData();

    public MainViewModel(@NonNull Application application) {
        super(application);

    }

    LiveData<DogImage> getImages(){
        return mutableLiveData;
    }

    LiveData<Boolean> getError(){
        return mutableError;
    }

    public LiveData<Boolean> getProgressBarReaction() {
        return progressBarReaction;
    }

    public void loadImages(){
        Disposable disposable =  loadImgRX()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        progressBarReaction.setValue(true);

                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Throwable {
                        progressBarReaction.setValue(false);
                    }
                })
                .subscribe(new Consumer<DogImage>() {
                    @Override
                    public void accept(DogImage dogImage) throws Throwable {
                        mutableError.setValue(false);
                        mutableLiveData.setValue(dogImage);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        mutableError.setValue(true);
                    }
                });
        compositeDisposable.add(disposable);

    }

    private Single<DogImage> loadImgRX(){
        return new ApiFactory().getApiService().getImage();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}

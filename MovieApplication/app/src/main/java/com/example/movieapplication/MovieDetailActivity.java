package com.example.movieapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imageViewPoster = findViewById(R.id.imageViewPosterDetail);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDetails = findViewById(R.id.textViewDetail);
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        if (movie != null) {
            Glide.with(this)
                    .load(movie.getPoster().getUrl())
                    .into(imageViewPoster);

            textViewTitle.setText(movie.getName());
            textViewYear.setText(String.valueOf(movie.getId()));
            textViewDetails.setText(movie.getDescription());
        }

        ApiFactory.apiservice.loadTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrailerRespons>() {
                    @Override
                    public void accept(TrailerRespons trailerRespons) throws Throwable {
                        Log.d("DEtail", trailerRespons.trailerList.getTrailers().get(0).toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("DEtail", throwable.toString());
                    }
                });


    }


    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }


}
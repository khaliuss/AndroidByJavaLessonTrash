package com.example.movieapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private ImageView imageViewPoster;
    private ImageView imageViewFavoriteIcon;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDetails;

    private RecyclerView trailerRecyclerView;

    private RecyclerView reviewRecyclerView;
    private ReviewAdapter reviewAdapter;
    private TrailersAdapter adapter;

    private  MovieDetailViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        init();

        reviewRecyclerView.setAdapter(reviewAdapter);

        trailerRecyclerView.setAdapter(adapter);
        adapter.setOnTrailerClickListener(new TrailersAdapter.OnTrailerClickListener() {
            @Override
            public void onClick(Trailer trailer) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(trailer.getUrl()));
                    startActivity(intent);
            }
        });

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

        if (movie != null) {
            Glide.with(this)
                    .load(movie.getPoster().getUrl())
                    .into(imageViewPoster);

            textViewTitle.setText(movie.getName());
            textViewYear.setText(String.valueOf(movie.getYear()));
            textViewDetails.setText(movie.getDescription());
        }


        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                adapter.setTrailers(trailers);
            }
        });

        viewModel.loadReviews(movie.getId());
        viewModel.getReviewsMLD().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviews(reviews);
            }
        });

        Drawable starOff = ContextCompat.getDrawable(MovieDetailActivity.this, android.R.drawable.star_off);
        Drawable starOn = ContextCompat.getDrawable(MovieDetailActivity.this, android.R.drawable.star_on);


        viewModel.getFavoriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {
                if (movieFromDb == null){
                    imageViewFavoriteIcon.setImageDrawable(starOff);
                    imageViewFavoriteIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.addMovie(movie);
                        }
                    });
                }else{
                    imageViewFavoriteIcon.setImageDrawable(starOn);
                    imageViewFavoriteIcon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.deleteMovie(movie.getId());
                        }
                    });
                }
            }
        });

        imageViewFavoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void init (){
        imageViewPoster = findViewById(R.id.imageViewPosterDetail);
        imageViewFavoriteIcon = findViewById(R.id.imageViewFavoriteIcon);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDetails = findViewById(R.id.textViewDetail);
        viewModel = new MovieDetailViewModel(getApplication());
        trailerRecyclerView = findViewById(R.id.trailersRecyclerView);
        adapter = new TrailersAdapter();
        reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
        reviewAdapter = new ReviewAdapter();

    }


    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }


}
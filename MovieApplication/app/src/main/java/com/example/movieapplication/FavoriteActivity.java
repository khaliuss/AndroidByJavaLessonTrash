package com.example.movieapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView favoriteRv;
    MoviesAdapter favoriteAdapter;
    FavoriteViewModel favoriteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        init();

        favoriteRv.setAdapter(favoriteAdapter);

        favoriteViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                favoriteAdapter.setMovies(movies);
            }
        });

        favoriteAdapter.setOnMovieClick(new MoviesAdapter.OnMovieClick() {
            @Override
            public void onClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(FavoriteActivity.this,movie);
                startActivity(intent);
            }
        });

    }

    public static Intent getInstance(Context context){
        Intent intent = new Intent(context,FavoriteActivity.class);
        return intent;
    }

    private void init(){
        favoriteRv = findViewById(R.id.favoriteRecyclerView);
        favoriteAdapter = new MoviesAdapter();
        favoriteViewModel = new FavoriteViewModel(getApplication());

    }
}
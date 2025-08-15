package com.example.movieapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {


    private List<Movie> movies = new ArrayList<Movie>();
    private OnReachEndOfList onReachEndOfList;
    private OnMovieClick onMovieClick;

    public void setOnMovieClick(OnMovieClick onMovieClick) {
        this.onMovieClick = onMovieClick;
    }

    public void setOnReachEndOfList(OnReachEndOfList onReachEndOfList) {
        this.onReachEndOfList = onReachEndOfList;
    }



    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);

        double ratingKp = movie.getRating().getRatingKp();

        int backgroundId;

        if (ratingKp>7){
            backgroundId = R.drawable.circle_green;
        }else if (ratingKp > 5){
            backgroundId = R.drawable.circle_orange;
        }else {
            backgroundId = R.drawable.circle_red;
        }

        holder.textViewRating.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),backgroundId));

        holder.textViewRating.setText(String.format("%.1f", movie.getRating().getRatingKp()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieClick.onClick(position);
            }
        });

        if (position == movies.size() - 10 && onReachEndOfList != null){
            onReachEndOfList.onReach();
        }
    }

    interface OnReachEndOfList{
        void onReach();
    }

    interface OnMovieClick{
        void onClick(int position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageViewPoster;
        private final TextView textViewRating;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}

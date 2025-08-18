package com.example.movieapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailerHolder>{

    private List<Trailer> trailers = new ArrayList<>();
    private OnTrailerClickListener onTrailerClickListener;

    public void setOnTrailerClickListener(OnTrailerClickListener onTrailerClickListener) {
        this.onTrailerClickListener = onTrailerClickListener;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_items,parent,false);

        return new TrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder holder, int position) {
        holder.trailerName.setText(trailers.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTrailerClickListener.onClick(trailers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    static class TrailerHolder extends RecyclerView.ViewHolder{

        TextView trailerName;
        public TrailerHolder(@NonNull View itemView) {
            super(itemView);

            trailerName = itemView.findViewById(R.id.trailerNameTextView);
        }
    }

    interface OnTrailerClickListener{

        void onClick(Trailer trailer);

    }

}

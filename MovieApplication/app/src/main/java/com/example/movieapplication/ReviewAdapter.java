package com.example.movieapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false);

        return new ReviewHolder(view);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        holder.author.setText(reviews.get(position).getAuthor());
        holder.review.setText(reviews.get(position).getReview());
        int type;
        switch (reviews.get(position).getType()) {
            case "Позитивный":
                type = R.color.green;
                break;
            case "Нейтральный":
                type = R.color.orange;
                break;
            case "Негативный":
                type = R.color.red;
                break;
            default:
                type = R.color.orange;
        }

        holder.container.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),type));

    }

    static class ReviewHolder extends RecyclerView.ViewHolder {

        private LinearLayout container;
        private TextView author;
        private TextView review;
        public ReviewHolder(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.reviewAuthor);

            review = itemView.findViewById(R.id.reviewText);

            container = itemView.findViewById(R.id.review_container);

        }
    }
}

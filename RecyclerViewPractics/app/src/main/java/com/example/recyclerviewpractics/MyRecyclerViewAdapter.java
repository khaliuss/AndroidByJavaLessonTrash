package com.example.recyclerviewpractics;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private List<String> strings;

    public MyRecyclerViewAdapter(List<String> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycler_view_item,
                        parent,
                        false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.textView.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.itemTextView);
        }
    }
}

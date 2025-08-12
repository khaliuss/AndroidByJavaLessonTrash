package com.example.todolistap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{

    ArrayList<Note> notes = new ArrayList<>();

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.to_do_item,
                parent,
                false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder viewHolder, int position) {
        Note note = notes.get(position);
        viewHolder.textView.setText(note.getText());

        int colorRes;
        switch (note.getPriority()){
            case 0:
                colorRes = android.R.color.holo_green_dark;
                break;
            case 1:
                colorRes = android.R.color.holo_orange_dark;
                break;
            default:
                colorRes = android.R.color.holo_red_dark;
                break;
        }
        int color = ContextCompat.getColor(viewHolder.itemView.getContext(),colorRes);
        viewHolder.textView.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.task_text);
        }
    }
}

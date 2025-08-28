package com.example.messengerbyfirebase;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageHolder> {

    private List<Message> messages = new ArrayList<>();
    private String currentUserId;



    private static final int VIEW_TYPE_MY_MESSAGE  = 100;
    private static final int VIEW_TYPE_OTHER_MESSAGE  = 101;

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public MessagesAdapter(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutResItem;
        if (viewType == VIEW_TYPE_MY_MESSAGE){
            layoutResItem = R.layout.my_message_item;
        }else {
            layoutResItem = R.layout.other_message_item;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResItem,parent,false);
        return new MessageHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        if (message.getSenderId().equals(currentUserId)){
            return VIEW_TYPE_MY_MESSAGE;
        }else {
            return  VIEW_TYPE_OTHER_MESSAGE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {

        Message message = messages.get(position);

        holder.textMessage.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageHolder extends RecyclerView.ViewHolder {

        TextView textMessage;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            textMessage = itemView.findViewById(R.id.textViewMessage);
        }
    }

}

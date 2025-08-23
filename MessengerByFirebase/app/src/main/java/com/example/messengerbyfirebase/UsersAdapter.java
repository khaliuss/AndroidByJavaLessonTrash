package com.example.messengerbyfirebase;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    private List<User> users = new ArrayList<>();

    private OnUserClickListener onUserClickListener;

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        User user = users.get(position);
        String userInfo = String.format("%s %s, %s", user.getName(), user.getLastName(), user.getAge());
        holder.name.setText(userInfo);
        int bgStatus;
        if (user.isOnline()) {
            bgStatus = R.drawable.circle_green;
        } else {
            bgStatus = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), bgStatus);
        holder.status.setBackground(background);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onUserClickListener != null) {
                    onUserClickListener.onUserClick(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    interface OnUserClickListener {
        void onUserClick(User user);
    }

    public class UsersHolder extends RecyclerView.ViewHolder {

        TextView name;
        View status;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewUserInfo);
            status = itemView.findViewById(R.id.onlineStatus);
        }
    }
}

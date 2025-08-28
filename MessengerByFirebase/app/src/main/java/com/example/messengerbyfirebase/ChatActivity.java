package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private static final  String EXTRA_CURRENT_USER_ID = "current_user_id";
    private static final String EXTRA_OTHER_USER_ID = "other_user_id";

    private TextView textViewTitle;
    private View onlineStatus;

    private RecyclerView recyclerViewMessages;
    private EditText editTextMessage;
    private ImageView imageViewSendMessage;
    private MessagesAdapter messagesAdapter;
    private String currentUserId;
    private String otherUserId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        otherUserId = getIntent().getStringExtra(EXTRA_OTHER_USER_ID);
        currentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);

        messagesAdapter = new MessagesAdapter(currentUserId);
        recyclerViewMessages.setAdapter(messagesAdapter);

        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            Message message = new Message("Text "+i,
                    currentUserId,
                    otherUserId
            );

            messages.add(message);
        }

        for (int i = 0; i < 10; i++) {
            Message message = new Message("Text "+i,
                    otherUserId,
                    currentUserId
            );
            messages.add(message);
        }
        messagesAdapter.setMessages(messages);


    }

    private void initView() {
        textViewTitle = findViewById(R.id.textViewTitle);
        onlineStatus = findViewById(R.id.statusView);
        recyclerViewMessages = findViewById(R.id.recyclerViewChat);
        editTextMessage = findViewById(R.id.editTextTyping);
        imageViewSendMessage = findViewById(R.id.imageViewSendMessage);

    }


    public static Intent newIntent(Context context,String currentUserId,String otherUserId){
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(EXTRA_CURRENT_USER_ID,currentUserId);
        intent.putExtra(EXTRA_OTHER_USER_ID,otherUserId);
        return intent;
    }
}
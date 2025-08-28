package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

    private ChatViewModelFactory chatViewModelFactory;
    private ChatViewModel chatViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();

        otherUserId = getIntent().getStringExtra(EXTRA_OTHER_USER_ID);
        currentUserId = getIntent().getStringExtra(EXTRA_CURRENT_USER_ID);

        chatViewModelFactory = new ChatViewModelFactory(currentUserId,otherUserId);
        chatViewModel = new ViewModelProvider(this,chatViewModelFactory)
                .get(ChatViewModel.class);



        messagesAdapter = new MessagesAdapter(currentUserId);
        recyclerViewMessages.setAdapter(messagesAdapter);
        observeViewModel();

        imageViewSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(editTextMessage.getText().toString().trim(),
                        currentUserId,
                        otherUserId
                );

                chatViewModel.sentMessage(message);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        chatViewModel.setUserOnline(true);

    }

    @Override
    protected void onPause() {
        super.onPause();

        chatViewModel.setUserOnline(false);
    }

    public void observeViewModel(){
        chatViewModel.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messagesAdapter.setMessages(messages);
            }
        });

        chatViewModel.getEror().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    Toast.makeText(ChatActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        chatViewModel.getMessageSent().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean send) {
                if (send){
                    editTextMessage.setText("");
                }
            }
        });

        chatViewModel.getOtherUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                String userInfo = String.format("%s %s ",user.getLastName(),user.getName());
                textViewTitle.setText(userInfo);
                int bgStatus;
                if (user.isOnline()) {
                    bgStatus = R.drawable.circle_green;
                } else {
                    bgStatus = R.drawable.circle_red;
                }
                Drawable background = ContextCompat.getDrawable(ChatActivity.this, bgStatus);
                onlineStatus.setBackground(background);
            }
        });
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
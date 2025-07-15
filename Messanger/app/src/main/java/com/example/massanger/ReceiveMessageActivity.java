package com.example.massanger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiveMessageActivity extends AppCompatActivity {

    TextView textViewReceivedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recive_message);

        textViewReceivedMessage = findViewById(R.id.textViewReceiveMessage);

        Intent intent = getIntent();
        String message =  intent.getStringExtra("Message");

        textViewReceivedMessage.setText(message);

    }
}
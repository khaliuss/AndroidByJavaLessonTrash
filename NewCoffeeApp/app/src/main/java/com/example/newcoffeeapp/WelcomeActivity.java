package com.example.newcoffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString().trim();
                String userPassword = password.getText().toString().trim();

                if (userName.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.fillDataToastWarning), Toast.LENGTH_SHORT).show();
                } else {
                   launchNextScreen(userName);
                }
            }
        });

    }

    private  void launchNextScreen(String userName){
        Intent intent = OrderActivity.newIntent(this,userName);
        startActivity(intent);
    }

    private void init() {
        name = findViewById(R.id.edSignInName);
        password = findViewById(R.id.edSignInPassword);
        signIn = findViewById(R.id.btSignIn);
    }
}
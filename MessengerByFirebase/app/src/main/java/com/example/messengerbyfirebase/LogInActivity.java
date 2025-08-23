package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private LogInViewModel viewModel;
    private EditText email;
    private EditText password;

    private TextView forgotPass;
    private TextView register;

    private Button signInBt;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context,LogInActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();

        observerViewModel();

        onClickListeners();
    }

    private void onClickListeners() {

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = RegistrationActivity.newIntent(LogInActivity.this);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sentEmail = "";
                if (!email.getText().toString().isEmpty()) {
                    sentEmail = email.getText().toString();
                }

                Intent intent = ForgotActivity.newIntent(LogInActivity.this, sentEmail);
                startActivity(intent);
            }
        });


        signInBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.signIn(email.getText().toString(), password.getText().toString());
            }
        });
    }

    private void observerViewModel() {

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String report) {
                if (report != null) {
                    Toast.makeText(LogInActivity.this, report, Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = MainActivity.newIntent(LogInActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    void init() {
        email = findViewById(R.id.lgiEmailEdTx);
        password = findViewById(R.id.lgiPassEdTx);
        signInBt = findViewById(R.id.signInButton);
        forgotPass = findViewById(R.id.forgotTvBt);
        register = findViewById(R.id.registerTvBt);
        viewModel = new ViewModelProvider(this).get(LogInViewModel.class);
    }
}
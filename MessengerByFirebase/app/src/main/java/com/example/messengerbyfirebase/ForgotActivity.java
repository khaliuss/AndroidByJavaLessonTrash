package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ForgotActivity extends AppCompatActivity {

    private EditText email;
    private static final String EMAIL_EXTRA = "Email";

    private Button regBt;
    private ForgotViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot);
        init();
        observerViewModel();

        String extraEmail= getIntent().getExtras().get(EMAIL_EXTRA).toString();
        email.setText(extraEmail);


        regBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.resetPass(email.getText().toString());
            }
        });




    }

    private void observerViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    Toast.makeText(ForgotActivity.this,s,Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.isSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success){
                    Toast.makeText(ForgotActivity.this, R.string.link_was_send,Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void init(){
        email = findViewById(R.id.fgEmailEdTx);
        regBt = findViewById(R.id.fgButton);
        viewModel = new ViewModelProvider(this).get(ForgotViewModel.class);
    }

    public static Intent newIntent(Context context,String email){
        Intent intent = new Intent(context, ForgotActivity.class);
        intent.putExtra("Email",email);
        return intent;
    }
}
package com.example.messengerbyfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {


    private RegisterViewModel viewModel;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;

    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
        observerViewModel();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getTrimmedValue(editTextEmail);
                String password = getTrimmedValue(editTextPassword);
                String name = getTrimmedValue(editTextName);
                String lastName = getTrimmedValue(editTextLastName);
                int age = Integer.parseInt(getTrimmedValue(editTextAge));
                viewModel.register(email,password,name,lastName,age);
            }
        });



    }


    private void observerViewModel(){
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null){
                    Toast.makeText(RegistrationActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null){
                    Intent intent = MainActivity.newIntent(RegistrationActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private String getTrimmedValue(EditText editText) {

        return editText.getText().toString().trim();

    }

    private void init(){
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        editTextEmail = findViewById(R.id.regEmailEdTx);
        editTextPassword = findViewById(R.id.regPasEdTx);
        editTextName = findViewById(R.id.regNameEdTx);
        editTextLastName = findViewById(R.id.regLNameEdTx);
        editTextAge = findViewById(R.id.editAgeText3);
        register = findViewById(R.id.regBt);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context,RegistrationActivity.class);
        return intent;
    }
}
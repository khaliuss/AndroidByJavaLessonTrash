package com.example.cafeemyveryfirstapplication;

import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.etLoginName);
        editTextPassword = findViewById(R.id.editTextPassword);


    }

    public void onClickCreateOrder(View view){
        String name = editTextName.getText().toString().trim();
        String password = editTextName.getText().toString().trim();

        if (!name.isEmpty() && !password.isEmpty()){
            Intent intent = new Intent(this, CreateOrderActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("password",password);
            startActivity(intent);
        }else{
            Toast.makeText(this,R.string.waringMessage,LENGTH_SHORT).show();
        }


    }
}
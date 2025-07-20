package com.example.newcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    EditText editText;

    Button button;

    TextView rightAnswer;
    TextView wrongAnswer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);
        rightAnswer = findViewById(R.id.rightAnswer);
        wrongAnswer = findViewById(R.id.wrongAnswer);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((editText.getText().toString()).equals("15")){
                    rightAnswer.setVisibility(View.VISIBLE);
                    wrongAnswer.setVisibility(View.INVISIBLE);
                }else{
                    wrongAnswer.setVisibility(View.VISIBLE);
                    rightAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });







    }
}
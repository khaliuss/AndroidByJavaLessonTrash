package com.example.habbites;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView score1;

    private TextView score2;

    private int bads =0;
    private int goods =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score1 = findViewById(R.id.textViewScore1);
        score2 = findViewById(R.id.textViewScore2);

        if (savedInstanceState != null){
            bads= savedInstanceState.getInt("bads");
            goods= savedInstanceState.getInt("goods");
        }



        score1.setText(String.valueOf(bads));
        score2.setText(String.valueOf(goods));

        score1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score1.setText(String.valueOf(++bads));
            }
        });

        score2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score2.setText(String.valueOf(++goods));
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("bads", bads);
        outState.putInt("goods", goods);
    }
}
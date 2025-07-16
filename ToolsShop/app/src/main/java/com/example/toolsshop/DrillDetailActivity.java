package com.example.toolsshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DrillDetailActivity extends AppCompatActivity {

    private TextView titleDrill;
    private TextView descriptionDrill;

    private ImageView iconDrill;

    private String title;
    private String description;
    private int icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill_detail);

        titleDrill = findViewById(R.id.titleDrill);
        descriptionDrill = findViewById(R.id.descriptionDrill);
        iconDrill = findViewById(R.id.iconDrill);


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        description=intent.getStringExtra("description");
        icon = intent.getIntExtra("icon",R.drawable.drill_icon);

        titleDrill.setText(title);
        descriptionDrill.setText(description);
        iconDrill.setImageDrawable(getDrawable(icon));




    }
}
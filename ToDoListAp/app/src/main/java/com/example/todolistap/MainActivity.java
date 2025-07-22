package com.example.todolistap;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addFAButton;
    private LinearLayout linearLayout;
    private ArrayList<Data> dataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            dataArrayList.add(new Data(i,random.nextInt(3),("String "+i)));
        }

        showItems();

    }

    private void showItems() {

        for (Data data:dataArrayList) {
            View view = getLayoutInflater().inflate(R.layout.to_do_item,linearLayout,false);

            TextView textView= view.findViewById(R.id.task_text);
            textView.setText(data.getText());
            int colorRes;
            switch (data.getPriority()){
                case 0:
                    colorRes = android.R.color.holo_green_dark;
                    break;
                case 1:
                    colorRes = android.R.color.holo_orange_dark;
                    break;
                default:
                    colorRes = android.R.color.holo_red_dark;
                    break;
            }
            int color = ContextCompat.getColor(this,colorRes);
            textView.setBackgroundColor(color);
            linearLayout.addView(view);
        }
    }

    private void initView() {
        addFAButton = findViewById(R.id.addFAButton);
        linearLayout = findViewById(R.id.linearContainer);
        dataArrayList = new ArrayList<>();
    }


}
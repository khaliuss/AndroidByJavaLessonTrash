package com.example.todolistap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addFAButton;
    private LinearLayout linearLayout;

    private DataBase dataBase = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        addFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddNewTaskActivity.newIntent(getApplicationContext());
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        showNotes();
    }

    private void showNotes() {
        linearLayout.removeAllViews();
        for (Note note:dataBase.getNotes()) {
            View view = getLayoutInflater().inflate(
                    R.layout.to_do_item,
                    linearLayout,
                    false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataBase.remove(note.getId());
                    showNotes();
                }
            });

            TextView textView= view.findViewById(R.id.task_text);
            textView.setText(note.getText());
            int colorRes;
            switch (note.getPriority()){
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
    }


}
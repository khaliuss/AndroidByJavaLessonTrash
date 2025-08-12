package com.example.todolistap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addFAButton;
    private RecyclerView recyclerView;
    private NotesAdapter recyclerViewAdapter;

    private DataBase dataBase = DataBase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        recyclerView.setAdapter(recyclerViewAdapter);

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
        recyclerViewAdapter.setNotes(dataBase.getNotes());
    }

    private void initView() {
        addFAButton = findViewById(R.id.addFAButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new NotesAdapter();
    }


}
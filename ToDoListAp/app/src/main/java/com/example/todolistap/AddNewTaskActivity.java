package com.example.todolistap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNewTaskActivity extends AppCompatActivity {

    private EditText editTextTask;
    private RadioButton rbLow;
    private RadioButton rbMedium;
    private Button saveBt;

    private NotesDataBase notesDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        initViews();

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }

    private void saveNote() {
        String text = editTextTask.getText().toString().trim();
        int priority = getPriority();
        Note note = new Note(priority,text);
        notesDataBase.notesDao().add(note);

        finish();
    }

    private int getPriority(){
        int priority;
        if(rbLow.isChecked()){
            priority = 0;
        } else if (rbMedium.isChecked()) {
            priority = 1;
        }else {
            priority =2;
        }
        return priority;
    }

    private void initViews() {
        notesDataBase = NotesDataBase.getInstance(getApplication());
        editTextTask = findViewById(R.id.editTextTask);
        rbLow = findViewById(R.id.rbLow);
        rbMedium = findViewById(R.id.rbMedium);
        saveBt = findViewById(R.id.saveBt);

    }

    public static Intent newIntent(Context context){
        return new Intent(context,AddNewTaskActivity.class);
    }








}
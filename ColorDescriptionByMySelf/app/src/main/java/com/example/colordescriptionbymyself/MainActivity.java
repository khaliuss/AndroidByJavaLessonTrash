package com.example.colordescriptionbymyself;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private Spinner spinnerColorDescription;
    private TextView textViewColorDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerColorDescription = findViewById(R.id.spinnerDescriptionColor);
        textViewColorDescription = findViewById(R.id.textViewDescription);


    }

    public void onButtonClick(View view) {
        int position = spinnerColorDescription.getSelectedItemPosition();


        textViewColorDescription.setText(getSpinnerColor(position));

    }

    private String getSpinnerColor(int position){
        String[] descriptionArray = getResources().getStringArray(R.array.description_of_temp);
        return descriptionArray[position];
    }

}
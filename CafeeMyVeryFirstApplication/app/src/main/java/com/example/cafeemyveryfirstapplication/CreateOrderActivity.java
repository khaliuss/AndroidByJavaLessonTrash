package com.example.cafeemyveryfirstapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateOrderActivity extends AppCompatActivity {

    TextView welcomeText;
    TextView additionText;
    CheckBox milkCheckBox;
    CheckBox sugarCheckBox;
    CheckBox lemonCheckBox;
    Spinner  spCoffeeKind;
    Spinner  spTeaKind;


    String name;
    String password;
    String drink;

    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        welcomeText = findViewById(R.id.tVGreetings);
        milkCheckBox = findViewById(R.id.chMilk);
        sugarCheckBox = findViewById(R.id.chSugar);
        lemonCheckBox = findViewById(R.id.chLemon);
        additionText = findViewById(R.id.tVWhatAdd);
        drink = getString(R.string.tea);
        spCoffeeKind = findViewById(R.id.spinnerCoffeeOption);
        spTeaKind = findViewById(R.id.spinnerTeaOption);
        stringBuilder = new StringBuilder();

        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")){
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }else{
            name = getString(R.string.greetingTextDefault);
            password = getString(R.string.defaultPasword);
        }
        String hello = String.format(getString(R.string.greetingTextName),name);
        welcomeText.setText(hello);


    }

    public void onDrinkChooseClicked(View view) {
        RadioButton radioButton = (RadioButton) view;

        if (radioButton.getId() == R.id.rCoffee){
            drink = getString(R.string.coffee);
            lemonCheckBox.setVisibility(View.INVISIBLE);
            spCoffeeKind.setVisibility(View.VISIBLE);
            spTeaKind.setVisibility(View.INVISIBLE);

        }else if (radioButton.getId() == R.id.rTea){
            drink = getString(R.string.tea);
            lemonCheckBox.setVisibility(View.VISIBLE);
            spCoffeeKind.setVisibility(View.INVISIBLE);
            spTeaKind.setVisibility(View.VISIBLE);
        }

        String additions = String.format("What add to you %s ",drink);
        additionText.setText(additions);

    }

    public void onClickOrder(View view) {
        stringBuilder.setLength(0);

        if (lemonCheckBox.isChecked() && drink.equals(getString(R.string.tea))){
            stringBuilder.append(getString(R.string.lemon)).append(" ");
        }

        if (milkCheckBox.isChecked()){
            stringBuilder.append(getString(R.string.milk)).append(" ");
        }

        if (sugarCheckBox.isChecked()){
            stringBuilder.append(getString(R.string.sugar)).append(" ");
        }

        String options ="";
        if (drink.equals(getString(R.string.tea))){
            options = spTeaKind.getSelectedItem().toString();
        }else if(drink.equals(getString(R.string.coffee))){
            options = spCoffeeKind.getSelectedItem().toString();
        }

        String order = String.format("Name: %s\nPassword: %s\nDrink: %s\nKind of Drink: %s\n",name,password,drink,options);
        String additions;

        if (stringBuilder.length()>0){
            additions = "Needed Additions: "+ stringBuilder.toString();
        }else {
            additions="";
        }
        String fullOrder = order+additions;

        Intent intent = new Intent(this,OrderDetailActivity.class);
        intent.putExtra("fullOrder",fullOrder);
        startActivity(intent);



    }
}
package com.example.newcoffeeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private String name;
    private String drink = "";

    private String type = "";


    private ArrayList<String> additions;

    Button orderButton;

    private TextView welcomeTextView;
    private TextView additionTextView;


    private CheckBox chbLemon;
    private CheckBox chbSugar;
    private CheckBox chbMilk;

    private Spinner teaSpinner;
    private Spinner coffeeSpinner;

    private RadioGroup radioGroup;
    private RadioButton rbTea;
    private RadioButton rbCoffee;

    private static final String USER_NAME = "userName";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        setUpUserName();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbTea.getId()){
                    teaChosen();
                }else if (checkedId == rbCoffee.getId()){
                    coffeeChosen();
                }
            }
        });
        drink = rbTea.getText().toString();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChoose();
            }
        });
    }

    private void userChoose() {
        if (rbTea.isChecked() && chbLemon.isChecked()){
            additions.add(chbLemon.getText().toString());
        }

        if (chbMilk.isChecked()){
            additions.add(chbMilk.getText().toString());
        }

        if (chbSugar.isChecked()){
            additions.add(chbSugar.getText().toString());
        }


        if (rbTea.isChecked()){
            type = teaSpinner.getSelectedItem().toString();
        }

        if (rbCoffee.isChecked()){
            type = coffeeSpinner.getSelectedItem().toString();
        }

        Intent intent = OrderDoneActivity.newIntent(this,name,drink,type,additions.toString());
        startActivity(intent);
    }


    public static Intent newIntent(Context context, String userName){
        Intent intent = new Intent(context,OrderActivity.class);
        intent.putExtra(USER_NAME,userName);
        return  intent;
    }


    private void teaChosen(){
        drink = rbTea.getText().toString();
        additionTextView.setText(getString(R.string.dynamicAdditionTV,rbTea.getText()));
        chbLemon.setVisibility(View.VISIBLE);
        teaSpinner.setVisibility(View.VISIBLE);
        coffeeSpinner.setVisibility(View.INVISIBLE);
    }

    private void coffeeChosen(){
        drink = rbCoffee.getText().toString();
        additionTextView.setText(getString(R.string.dynamicAdditionTV,rbCoffee.getText()));
        chbLemon.setVisibility(View.INVISIBLE);
        teaSpinner.setVisibility(View.INVISIBLE);
        coffeeSpinner.setVisibility(View.VISIBLE);
    }

    private void setUpUserName(){
        name = getIntent().getStringExtra(USER_NAME);
        welcomeTextView.setText(getString(R.string.dynamicWelcomeText,name));
    }

    private void init(){
        welcomeTextView = findViewById(R.id.tvWelcome);
        additionTextView = findViewById(R.id.tvAdditionInDrink);
        radioGroup = findViewById(R.id.radioGroup);
        rbTea = findViewById(R.id.rbTea);
        rbCoffee = findViewById(R.id.rbCoffee);
        chbLemon = findViewById(R.id.chbLemon);
        chbSugar = findViewById(R.id.chbSugar);
        chbMilk = findViewById(R.id.chbMilk);
        teaSpinner = findViewById(R.id.teaSpinner);
        coffeeSpinner = findViewById(R.id.coffeeSpinner);
        orderButton = findViewById(R.id.orderButton);
        additions = new ArrayList<>();
    }


}
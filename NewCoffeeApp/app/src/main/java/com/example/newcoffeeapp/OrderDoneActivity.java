package com.example.newcoffeeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDoneActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_DRINK = "EXTRA_DRINK";
    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    private static final String EXTRA_ADDITIONS = "EXTRA_ADDITIONS";

    private TextView name;
    private TextView drink;
    private TextView type;
    private TextView additions;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_done);
        viewInit();

        String nameTx = getIntent().getStringExtra(EXTRA_NAME);
        String drinkTx = getIntent().getStringExtra(EXTRA_DRINK);
        String typeTx = getIntent().getStringExtra(EXTRA_TYPE);
        String additionsTx = getIntent().getStringExtra(EXTRA_ADDITIONS);

        name.setText(nameTx);
        drink.setText(drinkTx);
        type.setText(typeTx);
        additions.setText(additionsTx);

    }

    private void viewInit() {
        name = findViewById(R.id.name);
        drink = findViewById(R.id.drink);
        type = findViewById(R.id.typeDrink);
        additions = findViewById(R.id.additions);
    }

    public static Intent newIntent(Context context,String name,String drink,String type,String additions){
       Intent intent = new Intent(context,OrderDoneActivity.class);
       intent.putExtra(EXTRA_NAME,name);
       intent.putExtra(EXTRA_DRINK,drink);
       intent.putExtra(EXTRA_TYPE,type);
       intent.putExtra(EXTRA_ADDITIONS,additions);
       return intent;
    }
}
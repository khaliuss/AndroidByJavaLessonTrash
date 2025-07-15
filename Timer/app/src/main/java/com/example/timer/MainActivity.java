package com.example.timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;
    private  int seconds= 0;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
        }
        timerText = findViewById(R.id.textView);
        runTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
        isRunning = false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        isRunning =true;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("isRunning",isRunning);
        outState.putInt("seconds",seconds);
    }

    public void onClickStart(View view) {
        isRunning = true;
    }

    public void onClickPause(View view) {
        isRunning =false;
    }

    public void onClickStop(View view) {
        isRunning =false;
        seconds=0;
    }


    private void runTimer(){
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hour,minutes,secs);
                timerText.setText(time);

                if (isRunning){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }

        });



    }

}
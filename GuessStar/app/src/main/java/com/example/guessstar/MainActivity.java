package com.example.guessstar;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        String nameString  = "Alex,Drake,Bob,Joshea";
        String[] names = nameString.split(",");
        for (String name:names){
            Log.i("MyName",name);
        }*/

        /*
        String geometry = "Geometry";
        String meter = geometry.substring(3,8);
        Log.i("MyName",meter);


        String river = "Mississippi";
        Pattern pattern = Pattern.compile("Mi(.*?)pi");
        Matcher matcher = pattern.matcher(river);

        while (matcher.find()){
            Log.i("MyName",matcher.group(1));
        }
        */


        String url = "<div class=\"image\">\n" +
                "\t\t\t\t\t\t<img decoding=\"async\" src=\"https://www.posh24.se/files/cdn-sub/images/-profile/02ac24d552f2499528ea43eb61685ec7b.jpg\" alt=\"Adam Alsing\"/>\n";

        Pattern imgPattern = Pattern.compile("src=\"(.*?)\"");
        Pattern namePattern = Pattern.compile("alt=\"(.*?)\"");

        Matcher nameMatcher = namePattern.matcher(url);
        Matcher imgMatcher = imgPattern.matcher(url);

        while (nameMatcher.find()){
            Log.i("MyName",nameMatcher.group(1));
        }

        while (imgMatcher.find()){
            Log.i("MyName",imgMatcher.group(1));
        }


    }
}
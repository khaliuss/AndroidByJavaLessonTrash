package com.example.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private String url = "https://samples.openweathermap.org/data/2.5/weather?q=London&appid=6b6f58680952b08543d1843c65ff4dce";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherDataTask weatherDataTask = new WeatherDataTask();

        weatherDataTask.execute(url);



    }

    private static class WeatherDataTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            URL url = null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder stringBuilder;

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                stringBuilder = new StringBuilder();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bf = new BufferedReader(reader);
                String line = bf.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = bf.readLine();
                }
                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            try {
                JSONObject jsonObject = new JSONObject(s);
                String name = jsonObject.getString("name");
                Log.i("Json",name);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
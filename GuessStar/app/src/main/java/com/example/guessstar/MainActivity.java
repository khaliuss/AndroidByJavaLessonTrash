package com.example.guessstar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String urlSite = "https://www.posh24.se/kandisar";
    ImageView imageViewPicture;
    Button button1;
    Button button2;
    Button button3;

    ArrayList<String> urls;
    ArrayList<String> names;

    private int numberOfQuestion;
    private int numberOfRightAnswer;
    private ArrayList<Button> buttons;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViewPicture = findViewById(R.id.imageView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttons = new ArrayList<>();

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);




        urls = new ArrayList<>();
        names = new ArrayList<>();


        getContent();
        playGame();




    }

    private void playGame(){
        generateQuestion();

        DownloadImages task = new DownloadImages();
        try {
            Bitmap bitmap = task.execute(urls.get(numberOfQuestion)).get();
            if (bitmap != null){
                imageViewPicture.setImageBitmap(bitmap);
                for (int i = 0; i < buttons.size(); i++) {
                    if (i == numberOfRightAnswer){
                        buttons.get(i).setText(names.get(numberOfQuestion));
                    }else{
                        int wrongAnswer = generateWrongAnswer();
                        buttons.get(i).setText(names.get(wrongAnswer));
                    }
                }
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private  void generateQuestion(){
        numberOfQuestion = (int)(Math.random() * names.size());
        numberOfRightAnswer = (int) (Math.random() * buttons.size()) ;
    }

    private int generateWrongAnswer(){

            int random = (int) (Math.random() * names.size());
            while (random == numberOfQuestion) {
                random = (int) (Math.random() * names.size());
                generateWrongAnswer();
            }
            return random;


    }

    private void getContent(){
        DownloadcontentTask content = new DownloadcontentTask();
        try {
            String urlContent = content.execute(urlSite).get();
            String start = "<p class=\"link\">Topp 100 kändisar</p>";
            String end = "<div class=\"col-xs-12 col-sm-6 col-md-4\">";
            Pattern pattern = Pattern.compile(start+"(.*?)"+end);
            Matcher matcher = pattern.matcher(urlContent);
            String splitContent = "";
            while (matcher.find()){
                splitContent = matcher.group(1);
            }

            Pattern patternImg  = Pattern.compile("src=\"(.*?)\"");
            Pattern patternName  = Pattern.compile("alt=\"(.*?)\"");
            Matcher imgMatcher = patternImg.matcher(splitContent);
            Matcher nameMatcher = patternName.matcher(splitContent);



            while (imgMatcher.find()){
                urls.add(imgMatcher.group(1));
            }

            while (nameMatcher.find()){
                names.add(nameMatcher.group(1));
            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void OnGuessClick(View view) {
        Button button = (MaterialButton) view;
        String  btText = button.getText().toString();

        if (btText.equals(buttons.get(numberOfRightAnswer).getText().toString())){
            Toast.makeText(this, "Right!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Wrong! Right answer is: "+names.get(numberOfQuestion), Toast.LENGTH_SHORT).show();
        }
        playGame();
    }

    private static class  DownloadImages extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {

            URL url = null;
            HttpURLConnection httpURLConnection = null;


            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream in = httpURLConnection.getInputStream();
                return BitmapFactory.decodeStream(in);




            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static  class DownloadcontentTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            URL url =null;
            HttpURLConnection httpURLConnection = null;
            StringBuilder stringBuilder = new StringBuilder();

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }

                return stringBuilder.toString();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
package com.mert.catchthecartman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class gamescreen extends AppCompatActivity {


    Intent mainMenu;

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Runnable runnable;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);



        //Image

        imageView =findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[] {imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};
        hideImages();


        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        score = 0;

        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: " + l / 1000);
            }

            @Override
            public void onFinish() {



                timeText.setText("Time Off !!");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(gamescreen.this);


                alert.setTitle("Game Over!");
                alert.setMessage("Are you sure to RESTART GAME ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //RESTART

                        Intent intent = getIntent();

                        finish();

                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        mainMenu = new Intent(gamescreen.this, MainActivity.class);

                        mainMenu.putExtra("endScore", score);

                        startActivity(mainMenu);



                    }
                });
                alert.show();

            }
        }.start();

    }

    public void increaseScore(View view) {



        score++;

        scoreText.setText("Score: " + score);





    }

    public void hideImages () {


        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {



                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }
        };
        handler.post(runnable);



    }

    public void exit(View view) {

        mainMenu = new Intent(gamescreen.this, MainActivity.class);

        startActivity(mainMenu);

    }

}
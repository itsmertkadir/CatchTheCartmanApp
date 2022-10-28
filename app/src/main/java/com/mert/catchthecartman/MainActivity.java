package com.mert.catchthecartman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    SharedPreferences scoreEnd;
    Intent gameScreen;
    TextView endScore;
    int score;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        endScore = findViewById(R.id.endScoreText);

        Intent scoreIntent = getIntent();


        score = scoreIntent.getIntExtra("endScore", 0);
        endScore.setText("End Score: " + score);



        scoreEnd = this.getSharedPreferences("com.mert.catchthecartman", Context.MODE_PRIVATE);
        scoreEnd.edit().putInt("endScore",score).apply();
        int score1 = scoreEnd.getInt("endScore", 0);

        endScore.setText("End Score: " + score1);



    }

    public void start(View view) {

        gameScreen = new Intent(MainActivity.this, gamescreen.class);
        startActivity(gameScreen);

    }
}
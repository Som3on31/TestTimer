package com.example.testtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tw;
    long interval = 1000;
    long inFuture = 3000;
    boolean isRunning = false;

    private final String TEST_WORD = "kek";

    Button startButton;

    Button stopButton;
    Button resetButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startButton = findViewById(R.id.start_button);
        stopButton = findViewById(R.id.stop_button);
        resetButton = findViewById(R.id.reset_button);

        new CountDownTimer(inFuture,interval){

            public void onTick(long l){
                String timeLeft = l/1000 + " s";
                tw.setText(timeLeft);
            }

            public void onFinish(){
                tw.setText(TEST_WORD);
                isRunning = false;
            }

        }.start();

        startButton.setVisibility(View.VISIBLE);
        startButton.setOnClickListener(view -> {
            if(!isRunning) {
                isRunning = true;
                startButton.setVisibility(View.INVISIBLE);
            }
        });

        stopButton.setOnClickListener(view -> {
            isRunning = false;
        });

        resetButton.setOnClickListener(view -> {

        });


    }



}
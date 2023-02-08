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

    Button startButton = (Button) findViewById(R.id.start_button);

    Button stopButton = (Button) findViewById(R.id.stop_button);
    Button resetButton = (Button) findViewById(R.id.reset_button);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new CountDownTimer(inFuture,interval){

            public void onTick(long l){
                tw.setText(l/1000 + " s");
            }

            public void onFinish(){
                tw.setText("kek");
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
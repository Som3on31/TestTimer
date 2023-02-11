package com.example.testtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Constant vars here
    protected final String TEST_WORD = "kek";
    protected TextView tw;
    protected long interval = 1000;
    protected int inFuture = 20*60;
    protected boolean isRunning = false;

    //Objs
    CountDownTimer timer;

    Button startButton;
//    Button stopButton;
//    Button resetButton;
//    Button pauseButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw = findViewById(R.id.timer);

        startButton = findViewById(R.id.start_button);

        startButton.setVisibility(View.VISIBLE);
        startButton.setOnClickListener(view -> {
            if(!isRunning) {
                isRunning = true;
                startButton.setVisibility(View.INVISIBLE);

                createTimer(inFuture);
                timer.start();
            }

        });

//        stopButton.setOnClickListener(view -> {
//            final String STOP_TXT = "Timer has stopped";
//
//            isRunning = false;
//            timer.cancel();
//            tw.setText(STOP_TXT);
//            startButton.setVisibility(View.VISIBLE);
//        });
//
//        resetButton.setOnClickListener(view -> {
//
//        });
//
//        pauseButton.setOnClickListener(view -> {
//            isRunning = false;
//
//        });


    }

    /**
     * Creates a timer with the set amount of time in seconds.
     *
     *
     * @param seconds time to be set for the timer
     */
    public void createTimer(int seconds){
        timer = new CountDownTimer(seconds * 1000,interval){

            public void onTick(long l){
                String timeLeftMinutes = l/60000 + ":";

                long remainingSeconds = (l/1000)%60;
                String timeLeftSeconds = remainingSeconds < 10 ? "0" + remainingSeconds : Long.toString(remainingSeconds);
                String timeLeft = timeLeftMinutes + timeLeftSeconds;
                tw.setText(timeLeft);
            }

            public void onFinish(){
                tw.setText(TEST_WORD);
                isRunning = false;
                startButton.setVisibility(View.VISIBLE);
            }

        };
    }



}

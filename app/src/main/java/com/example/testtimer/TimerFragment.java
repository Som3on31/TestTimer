package com.example.testtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

//import com.example.testtimer.databinding.ActivityMainBinding;
import androidx.navigation.Navigation;

import android.widget.TextView;

public class TimerFragment extends Fragment {

    //Constant vars here
    protected final String TEST_WORD = "kek";
    protected TextView tw;
    protected long interval = 1000;
    protected int inFuture = 20 * 60;
    //    protected int inFuture = 5;
    protected boolean isRunning = false;

    //Objects
    CountDownTimer timer;

    Button startButton;
    Button backButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


//        tw = findViewById(R.id.timer);
//
//        startButton = findViewById(R.id.start_button);
//
//        startButton.setVisibility(View.VISIBLE);
//        startButton.setOnClickListener(view -> {
//            if (!isRunning) {
//                isRunning = true;
//                startButton.setVisibility(View.INVISIBLE);
//
//                createTimer(inFuture);
//                timer.start();
//            }
//
//        });
//
//        backButton = findViewById(R.id.button);

//        backButton.setVisibility(View.VISIBLE);
//        backButton.setOnClickListener(view -> {
//
//        });
    }

    /**
     * Creates a timer with the set amount of time in seconds.
     *
     * @param seconds time to be set for the timer
     */
    public void createTimer(int seconds) {
        timer = new CountDownTimer(seconds * 1000L, interval) {

            public void onTick(long l) {
                long remainingMinutes = l / 60000;
                String timeLeftMinutes = makeNumTwoDigits(remainingMinutes) + ":";

                long remainingSeconds = (l / 1000) % 60;
                String timeLeftSeconds = makeNumTwoDigits(remainingSeconds);
                String timeLeft = timeLeftMinutes + timeLeftSeconds;
                tw.setText(timeLeft);
            }

            public void onFinish() {
//                tw.setText(TEST_WORD);
                isRunning = false;
                startButton.setVisibility(View.VISIBLE);
            }

        };
    }

    /**
     * Converts a long integer into a 2-digit number string.
     *
     * @param l a number to be used
     * @return a 2-digit number string.
     */
    private String makeNumTwoDigits(long l) {
        l %= 100;
        return l < 10 ? "0" + l : Long.toString(l);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }

    public void onDestroyView() {
        super.onDestroyView();

    }

    Button btn_menu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_timer, container,false);

        btn_menu = view.findViewById(R.id.button_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(com.google.android.material.R.id.container,homeFragment).commit();
            }
        });

        return view;
    }

}

package com.example.testtimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.example.testtimer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;

//    //Constant vars here
//    protected final String TEST_WORD = "kek";
//    protected TextView tw;
//    protected long interval = 1000;
//    protected int inFuture = 20 * 60;
//    //    protected int inFuture = 5;
//    protected boolean isRunning = false;

    //Objects
    CountDownTimer timer;

    Button startButton;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).commit();

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
////
//
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
//        NavController navController = navHostFragment.getNavController();


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
//
//        backButton.setVisibility(View.VISIBLE);
//        backButton.setOnClickListener(view -> {
//
//        });
    }

//    /**
//     * Creates a timer with the set amount of time in seconds.
//     *
//     * @param seconds time to be set for the timer
//     */
//    public void createTimer(int seconds) {
//        timer = new CountDownTimer(seconds * 1000L, interval) {
//
//            public void onTick(long l) {
//                long remainingMinutes = l / 60000;
//                String timeLeftMinutes = makeNumTwoDigits(remainingMinutes) + ":";
//
//                long remainingSeconds = (l / 1000) % 60;
//                String timeLeftSeconds = makeNumTwoDigits(remainingSeconds);
//                String timeLeft = timeLeftMinutes + timeLeftSeconds;
//                tw.setText(timeLeft);
//            }
//
//            public void onFinish() {
////                tw.setText(TEST_WORD);
//                isRunning = false;
//                startButton.setVisibility(View.VISIBLE);
//            }
//
//        };
//    }
//
//    /**
//     * Converts a long integer into a 2-digit number string.
//     *
//     * @param l a number to be used
//     * @return a 2-digit number string.
//     */
//    private String makeNumTwoDigits(long l) {
//        l %= 100;
//        return l < 10 ? "0" + l : Long.toString(l);
//    }


}

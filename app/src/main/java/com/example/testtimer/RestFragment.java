package com.example.testtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentRestBinding;

public class RestFragment extends Fragment {

    private FragmentRestBinding binding;

    protected long interval = 1000;
    protected int inFuture = 20;
    //    protected int inFuture = 5;
    protected boolean isRunning = false;

    private CountDownTimer timer;
    private TextView tw;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentRestBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.timer.findViewById(R.id.timer);

        binding.startButton.setVisibility(View.VISIBLE);
        binding.startButton.setOnClickListener(View -> {
            if (!isRunning) {
                isRunning = true;
                binding.startButton.setVisibility(View.INVISIBLE);

                createTimer(inFuture);
                timer.start();
            }
        });

        binding.button.setVisibility(View.VISIBLE);
        binding.button.setOnClickListener(View -> {
            NavHostFragment.findNavController(RestFragment.this)
                    .navigate(R.id.action_restFragment_to_homeFragment);
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //methods for time here
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
                if (binding != null) binding.timer.setText(timeLeft);
            }

            public void onFinish() {
//                tw.setText(TEST_WORD);
                isRunning = false;
                if (binding!=null) binding.startButton.setVisibility(View.VISIBLE);
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
}

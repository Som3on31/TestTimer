package com.example.testtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentTimerBinding;
import com.example.testtimer.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimerFragment extends Fragment {

    FragmentTimerBinding binding;

    protected long interval = 1000;
    protected int inFuture = 10;
    //    protected int inFuture = 5;
    protected boolean isRunning = false;

    private CountDownTimer timer;


    // User data here
    private FirebaseUser user;
    private DatabaseReference ref;
    private String userID;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentTimerBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.timer.findViewById(R.id.timer);

        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User u = snapshot.getValue(User.class);

                binding.points.setText(Integer.toString(u.points));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        binding.button.setOnClickListener(View -> NavHostFragment.findNavController(TimerFragment.this)
                .navigate(R.id.action_timerFragment_to_homeFragment));
    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;

//        user = null;
//        ref = null;
//        userID = null;
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
                binding.startButton.setVisibility(View.VISIBLE);

                if (binding != null) NavHostFragment.findNavController(TimerFragment.this)
                        .navigate(R.id.action_timerFragment_to_restFragment);
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

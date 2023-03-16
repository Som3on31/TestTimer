package com.example.testtimer;

import static com.example.testtimer.MainActivity.DEFAULT_REST_TIME_SECS;
import static com.example.testtimer.MainActivity.REST_TIME_TEXT;
import static com.example.testtimer.MainActivity.preference;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentRestBinding;
import com.example.testtimer.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RestFragment extends Fragment {

    private FragmentRestBinding binding;

    protected long interval = 1000;
    protected int inFuture = preference.getInt(REST_TIME_TEXT,DEFAULT_REST_TIME_SECS);
//    //    protected int inFuture = 5;
    protected boolean isRunning = false;

    private CountDownTimer timer;

    // get data from database
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
        binding = FragmentRestBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currentUser = snapshot.getValue(User.class);

                binding.points.setText(Integer.toString(currentUser.points));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getDetails(),Toast.LENGTH_SHORT);
                NavHostFragment.findNavController(RestFragment.this)
                        .navigate(R.id.action_restFragment_to_homeFragment);
            }
        });


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

        binding.btnNoRest.setOnClickListener(View -> {
            NavHostFragment.findNavController(RestFragment.this)
                    .navigate(R.id.action_restFragment_to_timerFragment);
        });

        binding.btnReward.setOnClickListener(View -> {
            NavHostFragment.findNavController(RestFragment.this)
                    .navigate(R.id.action_restFragment_to_rewardFragment);
        });

        binding.button.setVisibility(View.VISIBLE);
        binding.button.setOnClickListener(View -> {
            NavHostFragment.findNavController(RestFragment.this)
                    .navigate(R.id.action_restFragment_to_homeFragment);
        });

        String time = getTimer(preference.getInt(REST_TIME_TEXT,DEFAULT_REST_TIME_SECS) * interval);
        binding.timer.setText(time);
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
                String timeLeft = getTimer(l);
                if (binding != null) binding.timer.setText(timeLeft);
            }

            public void onFinish() {
                isRunning = false;
                if (binding!=null) binding.startButton.setVisibility(View.VISIBLE);

                //TODO: Update points via Firebase
                ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User u = snapshot.getValue(User.class);

                        u.points+= 10;

                        binding.points.setText(Integer.toString(u.points));
                        ref.child(userID).setValue(u);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(),error.getDetails(),Toast.LENGTH_SHORT);
                    }
                });
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

    private String getTimer(long l){
        long remainingMinutes = l / 60000;
        String timeLeftMinutes = makeNumTwoDigits(remainingMinutes) + ":";

        long remainingSeconds = (l / 1000) % 60;
        String timeLeftSeconds = makeNumTwoDigits(remainingSeconds);

        return timeLeftMinutes + timeLeftSeconds;
    }
}

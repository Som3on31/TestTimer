package com.example.testtimer;

import static com.example.testtimer.MainActivity.*;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.btnMenu.setOnClickListener(View -> NavHostFragment.findNavController(SettingsFragment.this)
                .navigate(R.id.action_settingsFragment_to_homeFragment)
        );


        //get the saved settings to show up on the screen
        loadSettings();

        //add a text watcher after this to check the input after
        //tapping out of the edit text. the rest won't be used.
        TextWatcher secondsWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int seconds = s.toString().equals(BLANK) ?
                        0 : Integer.parseInt(s.toString());
                SharedPreferences.Editor editor = preference.edit();

                if(seconds > DEFAULT_REST_TIME_SECS || (seconds <= 0 && s.toString().equals(BLANK))) {
                    seconds = seconds <= 0 ? 0 : DEFAULT_REST_TIME_SECS;

                    //TODO: Find a better solution to this. This way is very inefficient.
                    int finalSeconds = seconds;
                    binding.secs2.setOnFocusChangeListener((v, hasFocus) -> {
                        if (!hasFocus){
                            binding.secs2.removeTextChangedListener(this);
                            binding.secs2.setText(makeNumTwoDigits(finalSeconds));
                            binding.secs2.addTextChangedListener(this);
                        }
                    });

//                    System.out.println("Focus: " + focused);
//                    if (!focused){
//                        binding.secs2.removeTextChangedListener(this);
//                        binding.secs2.setText(makeNumTwoDigits(seconds));
//                        binding.secs2.addTextChangedListener(this);
//                    }
                }

                editor.putInt(REST_TIME_TEXT,seconds);
                editor.apply();


            }
        };
        binding.secs2.addTextChangedListener(secondsWatcher);
    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    //Methods regarding settings here

    /**
     * Load settings saved from shared preference defined in {@code MainActivity} class
     * and apply them to the screen.
     */
    private void loadSettings(){
        String textToSet = makeNumTwoDigits(preference
                .getInt(REST_TIME_TEXT, DEFAULT_REST_TIME_SECS));

        binding.secs2.setText(textToSet);
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
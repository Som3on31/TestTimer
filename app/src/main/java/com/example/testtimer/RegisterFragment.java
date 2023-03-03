package com.example.testtimer;

import static com.example.testtimer.MainActivity.BLANK;
import static com.example.testtimer.MainActivity.DEFAULT_REST_TIME_SECS;
import static com.example.testtimer.MainActivity.REST_TIME_TEXT;
import static com.example.testtimer.MainActivity.preference;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testtimer.databinding.FragmentRegisterBinding;
import com.example.testtimer.databinding.FragmentSettingsBinding;

public class RegisterFragment extends Fragment {

    FragmentRegisterBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //TODO: Make it support register fn
        binding.register.setOnClickListener(View -> {
            NavHostFragment.findNavController(RegisterFragment.this)
                    .navigate(R.id.action_registerFragment_to_loginFragment);
        });
    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}
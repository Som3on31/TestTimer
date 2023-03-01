package com.example.testtimer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   NavHostFragment.findNavController(SettingsFragment.this)
                           .navigate(R.id.action_settingsFragment_to_homeFragment);
               }
           }
        );



//        timer_min = binding.mins.getText();
//        timer_secs = binding.secs.getText();
//        rest_min = binding.mins2.getText();
//        rest_secs = binding.secs2.getText();

    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    //Methods regarding settings here
}
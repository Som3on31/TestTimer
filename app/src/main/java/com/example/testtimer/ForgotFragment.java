package com.example.testtimer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testtimer.databinding.FragmentForgotBinding;
import com.example.testtimer.databinding.FragmentRegisterBinding;


public class ForgotFragment extends Fragment {

    FragmentForgotBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForgotBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        //TODO: make it send an email to the des
        binding.submit.setOnClickListener(View -> {
            NavHostFragment.findNavController(ForgotFragment.this)
                    .navigate(R.id.action_forgotFragment_to_loginFragment);
        });
    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}
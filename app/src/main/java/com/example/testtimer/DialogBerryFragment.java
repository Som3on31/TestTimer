package com.example.testtimer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.testtimer.databinding.FragmentDialogBerryBinding;

public class DialogBerryFragment extends DialogFragment {

    FragmentDialogBerryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogBerryBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
//
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
//        super.onViewCreated(view,savedInstanceState);
//
//        getDialog().getWindow();
//    }

    @Override
    public void onResume() {

        getDialog().getWindow().setLayout(1000, 1200);
        // Call super onResume after sizing
        super.onResume();
    }

}
package com.example.testtimer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testtimer.databinding.FragmentDialogInfoBinding;


public class DialogInfoFragment extends DialogFragment {

    FragmentDialogInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogInfoBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onResume() {

        getDialog().getWindow().setLayout(1000, 1200);
        // Call super onResume after sizing
        super.onResume();
    }
}
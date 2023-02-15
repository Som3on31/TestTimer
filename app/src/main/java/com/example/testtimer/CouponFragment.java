package com.example.testtimer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testtimer.databinding.FragmentCouponBinding;
import com.example.testtimer.databinding.FragmentRestBinding;

public class CouponFragment extends Fragment {

    private FragmentCouponBinding binding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = FragmentCouponBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

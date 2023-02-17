package com.example.testtimer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
        //buttons on home screen
        binding.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CouponFragment.this)
                        .navigate(R.id.action_couponFragment_to_homeFragment2);
            }
        }
        );
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

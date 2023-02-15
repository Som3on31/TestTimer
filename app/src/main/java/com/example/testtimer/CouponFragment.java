package com.example.testtimer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.testtimer.databinding.FragmentCouponBinding;
import com.example.testtimer.databinding.FragmentRestBinding;

public class CouponFragment extends Fragment {

//    private FragmentCouponBinding binding;
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState){
//        binding = FragmentCouponBinding.inflate(inflater,container,false);
//
//        return binding.getRoot();
//    }

//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
//        super.onViewCreated(view,savedInstanceState);
//
//    }
//
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }




    Button btn_menu;
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_coupon, container,false);

        btn_menu = view.findViewById(R.id.button_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(com.google.android.material.R.id.container,homeFragment).commit();
            }
        });

        return view;
    }
}

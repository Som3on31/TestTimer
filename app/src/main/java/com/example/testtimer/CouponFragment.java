package com.example.testtimer;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        });

        binding.redeem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
//                   DialogCouponFragment dialogCouponFragment = new DialogCouponFragment();
//                   dialogCouponFragment.show(getContext());
                   AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                   alert.setTitle("Please show this redemption code to the merchant staff.");
                   alert.setMessage("\n Lkjdef \n");
                   AlertDialog dialog = alert.show();

                   TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                   messageView.setGravity(Gravity.CENTER);
                   messageView.setTextSize(40);
               }
           });

        binding.redeem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Please show this redemption code to the merchant staff.");
                alert.setMessage("\n Q1Plyk \n");
                AlertDialog dialog = alert.show();

                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
                messageView.setTextSize(40);
            }
        });

        binding.redeem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Please show this redemption code to the merchant staff.");
                alert.setMessage("\n Mko3sr \n");
                AlertDialog dialog = alert.show();

                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
                messageView.setTextSize(40);
            }
        });

        binding.redeem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Please show this redemption code to the merchant staff.");
                alert.setMessage("\n Ipkioj \n");
                AlertDialog dialog = alert.show();

                TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
                messageView.setTextSize(40);
            }
        });
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

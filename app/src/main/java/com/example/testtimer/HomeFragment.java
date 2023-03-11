package com.example.testtimer;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    AlertDialog.Builder builder;

    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        builder = new AlertDialog.Builder(getActivity());

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                createComfirmationPopup();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //buttons on home screen
        binding.imgTimer.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(HomeFragment.this)
                            .navigate(R.id.action_homeFragment_to_timerFragment);
                }
            }
        );

//        binding.imgTimer.setAlpha(0f);
//        binding.imgTimer.animate().alpha(1f).setDuration(1500);

        binding.imgCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_couponFragment);
            }
        }
        );

        binding.imgDistance.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 NavHostFragment.findNavController(HomeFragment.this)
                         .navigate(R.id.action_homeFragment_to_distanceFragment);
             }
         }
        );

        binding.imgBlf.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 NavHostFragment.findNavController(HomeFragment.this)
                         .navigate(R.id.action_homeFragment_to_blueLightFragment);
             }
         }
        );

        binding.imgSummary.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  NavHostFragment.findNavController(HomeFragment.this)
                          .navigate(R.id.action_homeFragment_to_summaryFragment);
              }
          }
        );

        binding.imgSetting.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  NavHostFragment.findNavController(HomeFragment.this)
                          .navigate(R.id.action_homeFragment_to_settingsFragment);
              }
          }
        );

        //login section
        binding.logout.setOnClickListener(View -> {
            createComfirmationPopup();

//            NavHostFragment.findNavController(HomeFragment.this)
//                    .navigate(R.id.action_homeFragment_to_loginFragment);
        });

        binding.imgBadge.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                DialogBerryFragment dialogBerryFragment = new DialogBerryFragment();
                dialogBerryFragment.show(getActivity().getSupportFragmentManager(), "description");
            }
        });

    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    //methods for handling logout
    public void createComfirmationPopup(){
        builder.setMessage("Would you like to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Toast.makeText(getContext(),"Successfully logged out",
                                Toast.LENGTH_SHORT).show();

                        NavHostFragment.findNavController(HomeFragment.this)
                                .navigate(R.id.action_homeFragment_to_loginFragment);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });


        AlertDialog logoutConfirmationPopup = builder.create();
        //Set title here
        logoutConfirmationPopup.setTitle("Confirm Logout?");
        logoutConfirmationPopup.show();
    }

}

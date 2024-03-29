package com.example.testtimer;


import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.testtimer.objects.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private AlertDialog.Builder builder;

    private FirebaseUser user;
    private DatabaseReference ref;
    private String userID;

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


        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

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

//        Bundle bundle = this.getArguments();
//        String data = bundle.getString("pfc");
//        binding.points.setText(data);
//        bundle.putString("pfc", binding.points.getText().toString());
//        getParentFragmentManager().setFragmentResult("pointsFromHome", result);

//        getParentFragmentManager().setFragmentResultListener("pointsFromCoupon", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String data = result.getString("pfc");
//                binding.points.setText(data);
//            }
//        });

        //Text
        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User currentUser = snapshot.getValue(User.class);

                int points = currentUser.points;
                String currentName = currentUser.username;

                binding.points.setText(Integer.toString(points));
                binding.username.setText(currentName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getDetails(),Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_loginFragment);
            }
        });


        //buttons on home screen
        binding.imgTimer.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(HomeFragment.this)
                            .navigate(R.id.action_homeFragment_to_timerFragment);
                }
            }
        );


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

        binding.btnInfo.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                DialogInfoFragment dialogInfoFragment = new DialogInfoFragment();
                dialogInfoFragment.show(getActivity().getSupportFragmentManager(), "information");
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
                .setPositiveButton("Yes", (dialog, id) -> {
                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(getContext(),"Successfully logged out",
                            Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(HomeFragment.this)
                            .navigate(R.id.action_homeFragment_to_loginFragment);
                })
                .setNegativeButton("No", (dialog, id) -> {
                    //  Action for 'NO' Button
                    dialog.cancel();

                });


        AlertDialog logoutConfirmationPopup = builder.create();
        //Set title here
        logoutConfirmationPopup.setTitle("Confirm Logout?");
        logoutConfirmationPopup.show();
    }

}

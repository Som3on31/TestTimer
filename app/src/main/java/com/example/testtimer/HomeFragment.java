package com.example.testtimer;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

//import com.example.testtimer.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

//    private FragmentHomeBinding binding;

//    public View onCreateView(
//            LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState
//    ) {
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//    }

//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        //buttons on home screen
//        binding.imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//                public void onClick(View view) {
//                    NavHostFragment.findNavController(HomeFragment.this)
//                            .navigate(R.id.action_homeFragment_to_restFragment);
//                }
//            }
//        );
//
//    }

//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }


//------------------------------------------------------------------------------------

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment(){
//
//    }
//
//    public static HomeFragment newInstance(String param1, String param2){
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle saveInstanceState){
//        super.onCreate(saveInstanceState);
//        if(getArguments() != null){
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
//        return inflater.inflate(R.layout.fragment_home, container,false);
//    }

    ImageButton btn_coupon;
    ImageButton btn_timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container,false);

        btn_coupon = view.findViewById(R.id.img_coupon);
        btn_timer = view.findViewById(R.id.img_timer);
        btn_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CouponFragment couponFragment = new CouponFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,couponFragment).addToBackStack(null).commit();
            }
        });

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimerFragment timerFragment = new TimerFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,timerFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}

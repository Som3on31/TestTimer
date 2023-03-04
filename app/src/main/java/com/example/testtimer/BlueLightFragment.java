package com.example.testtimer;


import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentBlueLightBinding;

public class BlueLightFragment extends Fragment {
    FragmentBlueLightBinding binding;

    Context deviceContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deviceContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  FragmentBlueLightBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        binding.blueLightToggleButton.setOnClickListener(View -> {
            int deviceState = deviceContext.getResources().getConfiguration().uiMode &
                    Configuration.UI_MODE_NIGHT_MASK;
            toggleNightLight(deviceContext,deviceState);
        });

        binding.btnMenu.setOnClickListener(View -> {
            NavHostFragment.findNavController(BlueLightFragment.this)
                    .navigate(R.id.action_blueLightFragment_to_homeFragment);
        });
    }

    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

    //methods regarding blue light filter
    public void toggleNightLight(Context target, int state){
        UiModeManager uiModeManager = (UiModeManager) target.getSystemService(Context.UI_MODE_SERVICE);

        switch (state){
            case Configuration.UI_MODE_NIGHT_YES: {
                uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                break;
            }
            case Configuration.UI_MODE_NIGHT_NO: {
                uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                break;
            }
            case Configuration.UI_MODE_NIGHT_UNDEFINED:{
                double a = 0;
                break;
            }
            default : {

            }
        }
    }
}
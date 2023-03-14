package com.example.testtimer;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentBlueLightBinding;

public class BlueLightFragment extends Fragment {
    FragmentBlueLightBinding binding;

    Context deviceContext;
    private WindowManager windowManager;
    private View overlayView;
    private View rootView;
    SeekBar alphaBar, redBar, greenBar, blueBar;
    TextView alphaText, redText, greenText, blueText;
    int alpha, red, green, blue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        binding =  FragmentBlueLightBinding.inflate(inflater, container, false);
        rootView = inflater.inflate(R.layout.fragment_blue_light, container, false);
        return rootView;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        //binding.getRoot();
        binding = FragmentBlueLightBinding.bind(rootView);
        binding.btnMenu.setOnClickListener(View -> {
            NavHostFragment.findNavController(BlueLightFragment.this)
                    .navigate(R.id.action_blueLightFragment_to_homeFragment);
        });
        Intent blueLightIntent = new Intent(getActivity(), BlueLightService.class);

        ToggleButton toggleButton = rootView.findViewById(R.id.toggle_button);


        //todo : send seekbar value to bluelight service
        alphaBar = rootView.findViewById(R.id.alpha_bar);
        alphaText = rootView.findViewById(R.id.alpha_text);
        redBar = rootView.findViewById(R.id.red_bar);
        redText = rootView.findViewById(R.id.red_text);
        greenBar = rootView.findViewById(R.id.green_bar);
        greenText = rootView.findViewById(R.id.green_text);
        blueBar = rootView.findViewById(R.id.blue_bar);
        blueText = rootView.findViewById(R.id.blue_text);
        alphaBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress;
                blueLightIntent.putExtra("alpha", alpha);
                alphaText.setText("Transparent = " + alpha);
                Log.d("alpha", "onProgressChanged: " + alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                blueLightIntent.putExtra("red", red);
                redText.setText("Red = " + red);
                Log.d("red", "onProgressChanged: " + red);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                blueLightIntent.putExtra("green", green);
                greenText.setText("Green = " + green);
                Log.d("green", "onProgressChanged: " + green);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                blueLightIntent.putExtra("blue", blue);
                blueText.setText("Blue = " + blue);
                Log.d("blue", "onProgressChanged: " + blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //when button clicked, send intent to bluelight service
        toggleButton.setOnClickListener(v -> {
            if(toggleButton.isChecked()){
                getActivity().startService(blueLightIntent);
            }
            else{
                getActivity().stopService(blueLightIntent);
            }
        });

    }

//    private void showOverlay() {
//        // Ask for permission to draw the overlay
//
//        // Create a new View to show the overlay
//        overlayView = new View(deviceContext);
//
//        overlayView.setBackgroundColor(Color.argb(20,0,60,100));
//
//
//        // Add the View to the WindowManager
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
//                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | //allow user to click through filter
//                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
//                PixelFormat.TRANSLUCENT);
//        windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        windowManager.addView(overlayView, params);
//    }
//    private void hideOverlay() {
//        // Remove the overlay View from the WindowManager
//        if (windowManager != null && rootView != null) {
//            windowManager.removeView(overlayView);
//            overlayView = null;
//            windowManager = null;
//        }
//    }
    public void onDestroyView() {
        super.onDestroyView();

        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
//        binding = null;
    }

}

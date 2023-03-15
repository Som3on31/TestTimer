package com.example.testtimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
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
    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceContext = getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        binding =  FragmentBlueLightBinding.inflate(inflater, container, false);
        rootView = inflater.inflate(R.layout.fragment_blue_light, container, false);
        mSharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
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

        //find all component in fragment
        ToggleButton toggleButton = rootView.findViewById(R.id.toggle_button);
        Button redPreset = rootView.findViewById(R.id.btn_red_preset);
        Button greenPreset = rootView.findViewById(R.id.btn_green_preset);
        Button yellowPreset = rootView.findViewById(R.id.btn_yellow_preset);
        //alpha
        alphaBar = rootView.findViewById(R.id.alpha_bar);
        int alphaProgress = mSharedPreferences.getInt("alphaValue", 0);
        alphaBar.setProgress(alphaProgress);
        alphaText = rootView.findViewById(R.id.alpha_text);
        //red
        redBar = rootView.findViewById(R.id.red_bar);
        int redProgress = mSharedPreferences.getInt("redValue", 0);
        redBar.setProgress(redProgress);
        redText = rootView.findViewById(R.id.red_text);
        //green
        greenBar = rootView.findViewById(R.id.green_bar);
        int greenProgress = mSharedPreferences.getInt("greenValue", 0);
        greenBar.setProgress(greenProgress);
        greenText = rootView.findViewById(R.id.green_text);
        //blue
        blueBar = rootView.findViewById(R.id.blue_bar);
        int blueProgress = mSharedPreferences.getInt("blueValue", 0);
        blueBar.setProgress(blueProgress);
        blueText = rootView.findViewById(R.id.blue_text);

        alphaBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                alpha = progress;
                blueLightIntent.putExtra("alpha", alpha);
                alphaText.setText("Transparent = " + alpha);
                if(toggleButton.isChecked()) startBlueLightService(blueLightIntent);
                    //getActivity().startService(blueLightIntent);
                Log.d("alpha", "onProgressChanged: " + alpha);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //save the progress value to sharedPreference
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("alphaValue", seekBar.getProgress());
                editor.apply();
            }
        });
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;
                blueLightIntent.putExtra("red", red);
                redText.setText("Red = " + red);
                if(toggleButton.isChecked()) getActivity().startService(blueLightIntent);;
                    //getActivity().startService(blueLightIntent);
                Log.d("red", "onProgressChanged: " + red);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //save the progress value to sharedPreference
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("redValue", seekBar.getProgress());
                editor.apply();
            }
        });
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;
                blueLightIntent.putExtra("green", green);
                greenText.setText("Green = " + green);
                if(toggleButton.isChecked()) getActivity().startService(blueLightIntent);;
                    //getActivity().startService(blueLightIntent);
                Log.d("green", "onProgressChanged: " + green);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //save the progress value to sharedPreference
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("greenValue", seekBar.getProgress());
                editor.apply();
            }
        });
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;
                blueLightIntent.putExtra("blue", blue);
                blueText.setText("Blue = " + blue);
                if(toggleButton.isChecked()) startBlueLightService(blueLightIntent);
                    //getActivity().startService(blueLightIntent);
                Log.d("blue", "onProgressChanged: " + blue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //save the progress value to sharedPreference
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("blueValue", seekBar.getProgress());
                editor.apply();
            }
        });
        redPreset.setOnClickListener(v -> setARGB(255, 124, 0, blueLightIntent, toggleButton));
        greenPreset.setOnClickListener(v -> setARGB(134, 255, 0, blueLightIntent, toggleButton));
        yellowPreset.setOnClickListener(v -> setARGB(230, 205, 0, blueLightIntent, toggleButton));


        //when button clicked, send intent to blueLight service
        toggleButton.setOnClickListener(v -> {
            if(toggleButton.isChecked()){
                //getActivity().startService(blueLightIntent);
                startBlueLightService(blueLightIntent);
            }
            else{
                //getActivity().stopService(blueLightIntent);
                requireActivity().stopService(blueLightIntent);
            }
        });

    }
    public void setARGB(int nRed, int nGreen, int nBlue, Intent intent, ToggleButton toggle){
        red = nRed;
        green=  nGreen;
        blue = nBlue;
        alpha = 100;
        intent.putExtra("alpha", alpha);
        intent.putExtra("red", red);
        intent.putExtra("green", green);
        intent.putExtra("blue", blue);
        alphaText.setText(R.string.transparent + alpha);
        redText.setText(R.string.red_value + red);
        greenText.setText(R.string.green_value + green);
        blueText.setText(R.string.blue_value + blue);
        if(toggle.isChecked()) startBlueLightService(intent);
            //getActivity().startService(intent);
        alphaBar.setProgress(alpha);
        redBar.setProgress(red);
        greenBar.setProgress(green);
        blueBar.setProgress(blue);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt("alphaValue", alphaBar.getProgress());
        editor.putInt("redValue", redBar.getProgress());
        editor.putInt("greenValue", greenBar.getProgress());
        editor.putInt("blueValue", blueBar.getProgress());
        editor.apply();

    }

    public void startBlueLightService(Intent intent){
        //if(toggleButton.isChecked()) getActivity().startService(intent);
        requireActivity().startService(intent);
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

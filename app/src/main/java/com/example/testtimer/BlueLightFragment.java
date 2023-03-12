//<<<<<<< Updated upstream
package com.example.testtimer;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.FragmentBlueLightBinding;

public class BlueLightFragment extends Fragment {
    FragmentBlueLightBinding binding;

    Context deviceContext;
    private WindowManager windowManager;
    private View overlayView;
    private View rootView;

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
        // TODO: 3/13/2023 need to return rootView to use bluelight filter, but if i do that, backbutton doesn't work 
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        binding.getRoot();
        //back to main menu button
//        binding.btnMenu.setOnClickListener(View -> NavHostFragment.findNavController(BlueLightFragment.this)
//                .navigate(R.id.action_blueLightFragment_to_homeFragment));

        // TODO: 3/13/2023 fix backbutton 
//        binding.btnMenu.setOnClickListener(View -> {
//            NavHostFragment.findNavController(BlueLightFragment.this)
//                    .navigate(R.id.action_blueLightFragment_to_homeFragment);
//        });
        Button backButton = rootView.findViewById(R.id.btn_menu);
        backButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(BlueLightFragment.this)
                    .navigate(R.id.action_blueLightFragment_to_homeFragment);
        });

        ToggleButton toggleButton = rootView.findViewById(R.id.toggle_button);
        toggleButton.setOnClickListener(v -> {
            if(toggleButton.isChecked()){
                showOverlay();
            }
            else{
                hideOverlay();
            }
        });
    }

    private void showOverlay() {
        // Ask for permission to draw the overlay
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getContext())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
//            startActivityForResult(intent, 0);
            startActivity(intent);
            return;
        }

        // Create a new View to show the overlay
        overlayView = new View(deviceContext);
        overlayView.setBackgroundColor(Color.argb(100,255,60,0));

        // Add the View to the WindowManager
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | //allow user to click through filter
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlayView, params);
    }
    private void hideOverlay() {
        // Remove the overlay View from the WindowManager
        if (windowManager != null && rootView != null) {
            windowManager.removeView(overlayView);
            overlayView = null;
            windowManager = null;
        }
    }
    public void onDestroyView() {
        super.onDestroyView();

        if (overlayView != null) {
            windowManager.removeView(overlayView);
        }
//        binding = null;
    }

}

//>>>>>>> Stashed changes

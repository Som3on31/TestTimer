package com.example.testtimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testtimer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    protected Context deviceContext;
    protected static SharedPreferences preference;


//    //Default settings set here
//    public static final int DEFAULT_REST_TIME = 20;
//    public static final int DEFAULT_MIN_DIST = 30;
//    public static final boolean DEFAULT_PLACEHOLDER = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        deviceContext = getApplicationContext();

        //get preference
        final String prefName = "app_settings";
        final int mode = MODE_PRIVATE;
        preference = deviceContext.getSharedPreferences(prefName,mode);
    }




}

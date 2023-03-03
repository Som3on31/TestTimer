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
    public static SharedPreferences preference;


//    //Default settings set here
    public static final String DEFAULT_REST_TIME_TEXT = "DEFAULT_REST_TIME";
    public static final String REST_TIME_TEXT = "REST_TIME";
    public static final int DEFAULT_REST_TIME_SECS = 20;
    public static final String BLANK = "";              //Everytime when it is empty


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
        preference = deviceContext.getSharedPreferences(prefName, MODE_PRIVATE);

        //Check if the app is freshly installed
        doValuesExist();
    }

    /**
     * Checks everytime when the app is first launched after installation.
     * It will ONLY run once.
     *
     */
    public void doValuesExist(){
        SharedPreferences.Editor editor = preference.edit();


        if (!preference.contains(DEFAULT_REST_TIME_TEXT)){
            editor.putInt(DEFAULT_REST_TIME_TEXT,DEFAULT_REST_TIME_SECS);
            editor.putInt(REST_TIME_TEXT,DEFAULT_REST_TIME_SECS);

            editor.apply();
        }
    }

}

package com.example.testtimer;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class BlueLightService extends Service implements View.OnTouchListener{
    private WindowManager mWindowManager;
    private View mOverlayView;
    int alpha, red, green, blue;


    public BlueLightService() {
    }
    // TODO: 3/14/2023 get temp value from seekbar in fragment and assign to alpha value

    @Override
    public void onCreate(){
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // Request overlay permission
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            stopSelf();
        } else {
            mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            WindowManager.LayoutParams params;

            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,//allow user to click through filter
                    PixelFormat.TRANSLUCENT
            );

            mOverlayView = new View(this);
            //mOverlayView.setBackgroundColor(Color.argb(50,100,60,0));
            mWindowManager.addView(mOverlayView, params);
        }


    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //retrieve the color value from the intent
        alpha = intent.getIntExtra("alpha", 50);
        red = intent.getIntExtra("red", 50);
        green = intent.getIntExtra("green", 100);
        blue = intent.getIntExtra("blue", 0);
        mOverlayView.setBackgroundColor(Color.argb(alpha, red, green, blue));
        Log.d("argb", "onStartCommand: alpha:"+ alpha + " red:" + red + " green:" + green + " blue:" + blue);
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event){
        //remove the view from the windowManager when it is touched
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.removeView(v);
        return true;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mWindowManager.removeView(mOverlayView);
    }
    public boolean isFilterOn(){
        if(mOverlayView != null) return true;
        else return false;
    }
}
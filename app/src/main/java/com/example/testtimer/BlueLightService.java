package com.example.testtimer;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class BlueLightService extends Service implements View.OnTouchListener{
    private WindowManager mWindowManager;
    private View mOverlayView;
    //int alpha;

    public BlueLightService() {
    }
    // TODO: 3/14/2023 get temp value from seekbar in fragment and assign to alpha value
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId){
//        alpha = intent.getIntExtra("alpha", -1);
//        Log.d("alpha", "onStartCommand: " + alpha);
//        return super.onStartCommand(intent, flags, startId);
//    }

    @Override
    public void onCreate(){
        super.onCreate();


        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams params = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
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
        }
        mOverlayView = new View(this);
        mOverlayView.setBackgroundColor(Color.argb(100,255,60,0));

        mWindowManager.addView(mOverlayView, params);

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
}
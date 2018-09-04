package com.example.sandeeplamsal123.memoreminder;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CallService extends JobIntentService {
    /**
     * Unique job ID for this service.
     */
    static final int JOB_ID = 1000;

    private WindowManager windowManager;
    private View popupView;
    boolean mHasDoubleClicked = false;
    long lastPressTime;
    private Boolean _enable = true;
    float dX, dY;

    /**
     * Convenience method for enqueuing work in to this service.
     */
    static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, CallService.class, JOB_ID, work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service created", Toast.LENGTH_LONG).show();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        addFloatingView(inflater);
    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {

    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "Service destroyed", Toast.LENGTH_LONG).show();
//
//        if (popupView != null) {
//            windowManager.removeView(popupView);
//        } else {
//            Log.e("PopupView", "I am null");
//        }
//
//    }


    private void addFloatingView(LayoutInflater inflater) {
        popupView = inflater.inflate(R.layout.activity_dialog, null);
        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        //Specify the view position
        params.gravity = Gravity.TOP | Gravity.LEFT;

        //Initially view will be added to top-left corner, you change x-y coordinates according to your need
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        windowManager.addView(popupView, params);

        Button btnDismiss = popupView.findViewById(R.id.btn_dismiss);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupView.setVisibility(View.GONE);
            }
        });

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                try {
                    popupView.setOnTouchListener(new View.OnTouchListener() {
                        private WindowManager.LayoutParams paramsF = params;
                        private int initialX;
                        private int initialY;
                        private float initialTouchX;
                        private float initialTouchY;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:

                                    // Get current time in nano seconds.
                                    long pressTime = System.currentTimeMillis();


                                    // If double click...
                                    if (pressTime - lastPressTime <= 300) {
//                                createNotification();
                                        CallService.this.stopSelf();
                                        mHasDoubleClicked = true;
                                    } else {     // If not double click....
                                        mHasDoubleClicked = false;
                                    }
                                    lastPressTime = pressTime;
                                    initialX = paramsF.x;
                                    initialY = paramsF.y;
                                    initialTouchX = event.getRawX();
                                    initialTouchY = event.getRawY();
                                    break;
                                case MotionEvent.ACTION_UP:
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    paramsF.x = initialX + (int) (event.getRawX() - initialTouchX);
                                    paramsF.y = initialY + (int) (event.getRawY() - initialTouchY);
                                    windowManager.updateViewLayout(popupView, paramsF);
                                    break;
                            }
                            return false;
                        }
                    });
                } catch (Exception e) {
                    // TODO: handle exception
                }

                return true;
            }
        });
    }

}


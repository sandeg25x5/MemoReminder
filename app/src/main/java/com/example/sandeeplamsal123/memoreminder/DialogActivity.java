//package com.example.sandeeplamsal123.memoreminder;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//public class DialogActivity extends Activity implements View.OnTouchListener {
//    private Button btnDissmiss;
//    private LinearLayout linearDialog;
//    private int _xDelta;
//    private int _yDelta;
//    float dX, dY;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dialog);
//
//        linearDialog = findViewById(R.id.linear_dialog);
//        btnDissmiss = findViewById(R.id.btn_dismiss);
//        linearDialog.setOnTouchListener(this);
//
//
//        btnDissmiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//    }
//
//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
//                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
//                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
//                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
//                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
//    }
//
//    @Override
//    public boolean onTouch(View view, MotionEvent event) {
//
//        switch (event.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//
//                dX = view.getX() - event.getRawX();
//                dY = view.getY() - event.getRawY();
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                view.animate()
//                        .x(event.getRawX() + dX)
//                        .y(event.getRawY() + dY)
//                        .setDuration(0)
//                        .start();
//                break;
//            default:
//                return false;
//        }
//        return true;
//    }
//}

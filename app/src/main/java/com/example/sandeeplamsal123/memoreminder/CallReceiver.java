package com.example.sandeeplamsal123.memoreminder;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.JobIntentService;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class CallReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String phoneState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (phoneState.equals(TelephonyManager.EXTRA_STATE_RINGING) || phoneState.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "The Permission is not granted.", Toast.LENGTH_SHORT).show();
            } else {
                CallService.enqueueWork(context, intent);
            }
        } else if (phoneState.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            Toast.makeText(context, "The Permission is not granted. in idle state", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "The Permission is not granted. in other state", Toast.LENGTH_SHORT).show();

        }
    }

}

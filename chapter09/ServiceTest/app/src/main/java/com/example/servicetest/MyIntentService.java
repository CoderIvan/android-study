package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Ivan on 2015/6/14.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("MyIntentService", "Thread is + " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }
}

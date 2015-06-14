package com.example.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

/**
 * Created by Ivan on 2015/6/14.
 */
public class LongRunningService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed @ " + new Date().toString());
            }
        }).start();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int time = 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + time;
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(LongRunningService.this, AlarmReceiver.class), 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}

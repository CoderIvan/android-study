package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Ivan on 2015/6/14.
 */
public class MyService extends Service {

    private Binder binder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new Notification.Builder(MyService.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notification comes")
                .setContentTitle("This is title")
                .setContentText("This is content")
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0))
                .build();
        startForeground(1, notification);
        Log.d("MyService", "onCreate executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "onDestroy executed");
        super.onDestroy();
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "startDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "getProgress executed");
            return 0;
        }
    }
}

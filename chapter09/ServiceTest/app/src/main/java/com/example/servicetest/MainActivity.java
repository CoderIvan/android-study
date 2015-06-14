package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;

    private MyService.DownloadBinder downloadBinder;

    private Button startIntentService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService", "onServiceConnected executed");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService", "onServiceDisconnected executed");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Button) findViewById(R.id.start_service);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        stopService = (Button) findViewById(R.id.stop_service);
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

        bindService = (Button) findViewById(R.id.bind_service);
        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });

        unbindService = (Button) findViewById(R.id.unbind_service);
        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "Thread id is " + Thread.currentThread().getId());
                Intent intentService = new Intent(MainActivity.this, MyIntentService.class);
                startService(intentService);
            }
        });
    }
}

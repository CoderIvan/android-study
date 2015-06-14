package com.example.androidthreadtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView text;

    private Button changeText;

    private Handler handler= new Handler() {
        @Override
        public void handleMessage(Message msg) {
            text.setText("Nice to meet you");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}

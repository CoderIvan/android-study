package com.example.networktest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends Activity {

    public static final int SHOW_RESPONSE = 0;

    private Button sendRequest;

    private Button parse;

    private TextView responseText;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    responseText.setText((String) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseText = (TextView) findViewById(R.id.response_text);

        sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.sendHttpRequest("http://www.baidu.com", new HttpUtil.HttpCallbackListener() {

                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = e;
                        handler.sendMessage(message);
                    }
                });
            }
        });

        parse = (Button) findViewById(R.id.parse);
        parse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                message.obj = JSON() + "\n" + GSON();
                handler.sendMessage(message);
            }
        });
    }

    private String JSON() {
        String json = "{'name':'Ivan', 'age':27}";
        Message message = new Message();
        message.what = SHOW_RESPONSE;
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            return "[JSON] Name is " + object.getString("name") + " && Age = " + object.getInt("age");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String GSON() {
        String jsonData = "{'id':5, 'name':'Angry Birds', 'version':5.5}";
        Gson gson = new Gson();
        App app = gson.fromJson(jsonData, new TypeToken<App>() {
        }.getType());
        return "[GSON] Id is " + app.getId() + "Name is " + app.getName() + " && Version = " + app.getVersion();
    }
}

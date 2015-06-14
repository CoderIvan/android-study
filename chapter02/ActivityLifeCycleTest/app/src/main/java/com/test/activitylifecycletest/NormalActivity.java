package com.test.activitylifecycletest;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Ivan on 2015/6/7.
 */
public class NormalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.normal_layout);
    }
}

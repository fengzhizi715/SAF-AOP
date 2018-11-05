package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.safframework.aop.annotation.Safe;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForSafeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Safe(callBack = "doCallBack")
    private void initData() {

        String s = null;
        int length = s.length();
    }

    private void doCallBack() {

        Toast.makeText(DemoForSafeActivity.this, "invoke the doCallBack method", Toast.LENGTH_SHORT).show();
    }
}

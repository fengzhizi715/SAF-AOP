package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;

import com.safframework.app.annotation.Safe;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForSafeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Safe
    private void initData() {

        String s = null;
        int length = s.length();
    }
}

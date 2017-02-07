package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.safframework.app.annotation.Async;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForAsyncActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Async
    private void initData() {

        StringBuilder sb = new StringBuilder();
        sb.append("current thread=").append(Thread.currentThread().getId())
                .append("\r\n")
                .append("ui thread=")
                .append(Looper.getMainLooper().getThread().getId());


        Toast.makeText(DemoForAsyncActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
    }
}

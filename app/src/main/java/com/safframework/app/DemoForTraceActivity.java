package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;

import com.safframework.app.annotation.Trace;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForTraceActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Trace
    private void initData() {

        for (int i=0;i<10000;i++) {
            Map map = new HashMap();
            map.put("name","tony");
            map.put("age","18");
            map.put("gender","male");
        }
    }
}

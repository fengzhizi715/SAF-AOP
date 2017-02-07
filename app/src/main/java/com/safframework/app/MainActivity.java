package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;

import com.safframework.injectview.Injector;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.injectInto(this);
    }
}

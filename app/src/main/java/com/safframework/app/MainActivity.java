package com.safframework.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.OnClick;

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

    @OnClick(id={R.id.text1})
    void clickText1() {

        Intent i = new Intent(MainActivity.this,DemoForAsyncActivity.class);
        startActivity(i);
    }

    @OnClick(id={R.id.text2})
    void clickText2() {

        Intent i = new Intent(MainActivity.this,DemoForCacheableActivity.class);
        startActivity(i);
    }

    @OnClick(id={R.id.text3})
    void clickText3() {

        Intent i = new Intent(MainActivity.this,DemoForLogMethodActivity.class);
        startActivity(i);
    }

    @OnClick(id={R.id.text4})
    void clickText4() {

        Intent i = new Intent(MainActivity.this,DemoForHookMethodActivity.class);
        startActivity(i);
    }

    @OnClick(id={R.id.text6})
    void clickText6() {

        Intent i = new Intent(MainActivity.this,DemoForSafeActivity.class);
        startActivity(i);
    }

    @OnClick(id={R.id.text7})
    void clickText7() {

        Intent i = new Intent(MainActivity.this,DemoForTraceActivity.class);
        startActivity(i);
    }
}

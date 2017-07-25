package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;

import com.safframework.aop.annotation.HookMethod;
import com.safframework.log.L;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForHookMethodActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        testRx();
    }

    @HookMethod(beforeMethod = "method1",afterMethod = "method2")
    private void initData() {

        L.i("initData()");
    }

    private void method1() {
        L.i("method1() is called before initData()");
    }

    private void method2() {
        L.i("method2() is called after initData()");
    }

    private void testRx() {

        Observable.just("tony")
                .subscribe(new Consumer<String>() {

                    @HookMethod(beforeMethod = "testRxBefore")
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("s="+s);
                    }

                    private void testRxBefore() {
                        L.i("testRxBefore() is called before accept()");
                    }
                });
    }

}

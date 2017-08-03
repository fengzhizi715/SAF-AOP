package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;

import com.safframework.aop.annotation.Trace;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForTraceActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Trace(enable = false)
    private void initData() {

        Observable.create(new ObservableOnSubscribe<String>() {

            @Trace
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {

                e.onNext("111");
                e.onNext("222");
                e.onNext("333");

            }
        }).subscribe(new Consumer<String>() {

            @Trace
            @Override
            public void accept(@NonNull String str) throws Exception {

            }
        });
    }
}

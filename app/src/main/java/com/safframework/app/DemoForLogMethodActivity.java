package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;

import com.safframework.app.annotation.LogMethod;
import com.safframework.app.domain.User;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForLogMethodActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData1();

        initData2("test");

        User u = new User();
        u.name = "tony";
        u.password = "123456";
        initData3(u);
    }

    @LogMethod
    private void initData1() {
    }

    @LogMethod
    private String initData2(String s) {

        return s;
    }

    @LogMethod
    private User initData3(User u) {

        u.password = "abcdefg";

        return u;
    }
}

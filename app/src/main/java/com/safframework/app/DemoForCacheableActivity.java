package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.safframework.app.annotation.Cacheable;
import com.safframework.app.domain.Address;
import com.safframework.cache.Cache;
import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.OnClick;
import com.safframwork.tony.common.utils.StringUtils;

/**
 * Created by Tony Shen on 2017/2/7.
 */

public class DemoForCacheableActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_for_cacheable);
        Injector.injectInto(this);

        initData();
    }

    @Cacheable(key = "address")
    private Address initData() {

        Address address = new Address();
        address.country = "China";
        address.province = "Jiangsu";
        address.city = "Suzhou";
        address.street = "Ren min Road";

        return address;
    }

    @OnClick(id={R.id.text})
    void clickText() {

        Cache cache = Cache.get(this);
        Address address = (Address) cache.getObject("address");
        Toast.makeText(this, StringUtils.printObject(address),Toast.LENGTH_SHORT).show();
    }
}

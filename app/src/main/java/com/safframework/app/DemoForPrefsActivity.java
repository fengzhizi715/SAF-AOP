package com.safframework.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.safframework.app.annotation.Prefs;
import com.safframework.app.domain.Article;
import com.safframework.injectview.Injector;
import com.safframework.injectview.annotations.OnClick;
import com.safframework.prefs.AppPrefs;
import com.safframwork.tony.common.utils.StringUtils;

/**
 * Created by Tony Shen on 2017/2/8.
 */

public class DemoForPrefsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_for_prefs);
        Injector.injectInto(this);

        initData();
    }

    @Prefs(key = "article")
    private Article initData() {

        Article article = new Article();
        article.author = "tony";
        article.title = "kotlin in action";
        article.createDate = "2017-01-02";
        article.content = "just a test...";

        return article;
    }

    @OnClick(id={R.id.text})
    void clickText() {

        AppPrefs appPrefs = AppPrefs.get(this);
        Article article = (Article) appPrefs.getObject("article");
        Toast.makeText(this, StringUtils.printObject(article),Toast.LENGTH_SHORT).show();
    }
}

# SAF-AOP

[![@Tony沈哲 on weibo](https://img.shields.io/badge/weibo-%40Tony%E6%B2%88%E5%93%B2-blue.svg)](http://www.weibo.com/fengzhizi715)
 [ ![Download](https://api.bintray.com/packages/fengzhizi715/maven/saf-aop/images/download.svg) ](https://bintray.com/fengzhizi715/maven/saf-aop/_latestVersion)
[![License](https://img.shields.io/badge/license-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
<a href="http://www.methodscount.com/?lib=com.safframework%3Asaf-aop%3A1.2.0"><img src="https://img.shields.io/badge/Methods and size-core: 108 | deps: 923 | 29 KB-e91e63.svg"/></a>

![](logo.png)

# 下载安装

在根目录下的build.gradle中添加
```groovy
buildscript {
     repositories {
         jcenter()
     }
     dependencies {
         classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:1.1.1'
         classpath 'org.aspectj:aspectjtools:1.8.13'
     }
 }
```

在app 模块目录下的build.gradle中添加
```groovy
apply plugin: 'com.hujiang.android-aspectjx'

...

dependencies {
    compile 'com.safframework:saf-aop:1.2.1'
    ...
}
```

基于aspectj的AOP，无需使用耗费性能的反射.不过,需要在build.gradle中配置一下aspectj


| 注解名称        | 作用          | 备注          |
| ------------- |:-------------:| :-------------:|
| @Async        |借助rxjava,异步执行app中的方法|       |
| @Cacheable    |Spring Cache风格的Cache注解,将结果放于缓存中|只适用于android4.0以后|
| @LogMethod    |将方法的入参和出参都打印出来,可以用于调试|       |
| @HookMethod   |可以在调用某个方法之前、以及之后进行hook|比较适合埋点的场景，可以单独使用也可以跟任何自定义注解配合使用。也支持在匿名内部类中使用|
| @Prefs        |将方法返回的结果放入AppPrefs中|只适用于android4.0以后|
| @Safe         |可以安全地执行方法,而无需考虑是否会抛出运行时异常|       |
| @Trace        |用于追踪某个方法花费的时间,可以用于性能调优的评判|支持追踪匿名内部类中的方法       |


@Async的使用方法:
---
```Java
	@Async
	private void useAsync() {
		Log.e(TAG, " thread=" + Thread.currentThread().getId());
		Log.e(TAG, "ui thread=" + Looper.getMainLooper().getThread().getId());
	}
```

@Cacheable的使用方法:
---
```Java
	@Cacheable(key = "user")
	private User initData() {
		User user = new User();
		user.userName = "tony";
		user.password = "123456";
		return user;
	}
```

这里的@Cacheable,实际上用到[Cache](https://github.com/fengzhizi715/SAF/blob/master/docs/cache.md),要获取Cache也很简单.

@Trace的使用方法:
---
```Java
	@Trace
	@Async
	private void loadUser() {
		Log.e(TAG, " thread=" + Thread.currentThread().getId());
		Log.e(TAG, "ui thread=" + Looper.getMainLooper().getThread().getId());
		Cache cache = Cache.get(this);
		User user = (User) cache.getObject("user");
		Toast.makeText(MainActivity.this, SAFUtils.printObject(user), Toast.LENGTH_SHORT).show();
	}
```
将@Trace和@Async两个注解结合使用,可以看到调用loadUser()方法花费的时间.
```Java
05-18 14:31:31.229 21190-21190/app.magicwindow.cn.testsaf I/MainActivity: MainActivity=loadUser() take [1ms]
05-18 14:31:31.231 21190-22033/app.magicwindow.cn.testsaf E/com.test.saf.activity.MainActivity:  thread=14876
05-18 14:31:31.231 21190-22033/app.magicwindow.cn.testsaf E/com.test.saf.activity.MainActivity: ui thread=1
```


@Trace还支持在匿名内部类中使用

```java
    @Trace
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
```

@HookMethod的使用方法:
---
不写beforeMethod和afterMethod，则相当于没有使用@HookMethod<br>
beforeMethod和afterMethod对应的都是方法名，分别表示在调用doSomething()之前执行和之后执行。目前还不支持在beforeMethod和afterMethod中传递参数。

```Java
   @HookMethod(beforeMethod="dosthbeforeMethod",afterMethod="dosthafterMethod")
   void doSomething() {

   }
```

@HookMethod 同样支持在匿名内部类中使用

```java
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
```

Proguard
===
```java
-keep class com.safframework.aop.** { *; }
```

联系方式
===

Wechat：fengzhizi715

SAF-AOP相关文章：

http://www.jianshu.com/p/9e78560cadad

http://www.jianshu.com/p/2779e3bb1f14

ChangeLog
===
[版本更新记录](CHANGELOG.md)

License
-------

    Copyright (C) 2017 Tony Shen.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

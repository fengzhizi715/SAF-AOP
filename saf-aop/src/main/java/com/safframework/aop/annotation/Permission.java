package com.safframework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @FileName: com.safframework.aop.annotation.Permission
 * @author: Tony Shen
 * @date: 2018-11-06 15:53
 * @version: V1.0 <描述当前版本功能>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    String[] value();

    int requestCode() default 1;
}

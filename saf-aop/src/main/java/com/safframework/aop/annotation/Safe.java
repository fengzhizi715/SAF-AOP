package com.safframework.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by Tony Shen on 16/3/23.
 */
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Safe {

    String callBack() default "";
}

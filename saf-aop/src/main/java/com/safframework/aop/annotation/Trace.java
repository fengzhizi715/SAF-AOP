package com.safframework.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by Tony Shen on 16/3/22.
 */
@Target({METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.CLASS)
public @interface Trace {
}

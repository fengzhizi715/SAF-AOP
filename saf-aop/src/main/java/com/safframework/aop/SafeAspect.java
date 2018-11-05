package com.safframework.aop;

import com.safframework.log.L;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Tony Shen on 16/3/23.
 */
@Aspect
public class SafeAspect {

    private static final String POINTCUT_METHOD = "execution(@com.safframework.aop.annotation.Safe * *(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithSafe() {
    }

    @Around("methodAnnotatedWithSafe()")
    public Object safeMethod(final ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            L.w(getStringFromException(e));
        }
        return result;
    }

    private static String getStringFromException(Throwable ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}

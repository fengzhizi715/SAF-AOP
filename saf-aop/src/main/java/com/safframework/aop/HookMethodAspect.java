package com.safframework.aop;

import com.safframework.aop.annotation.HookMethod;
import com.safframework.log.L;
import com.safframework.tony.common.reflect.Reflect;
import com.safframework.tony.common.reflect.ReflectException;
import com.safframework.tony.common.utils.Preconditions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


/**
 * Created by Tony Shen on 2016/12/7.
 */
@Aspect
public class HookMethodAspect {

    private static final String POINTCUT_METHOD = "execution(@com.safframework.aop.annotation.HookMethod * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.safframework.aop.annotation.HookMethod *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithTrace() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedTrace() {
    }

    @Around("methodAnnotatedWithTrace() || constructorAnnotatedTrace()")
    public void hookMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        HookMethod hookMethod = method.getAnnotation(HookMethod.class);

        if (hookMethod==null) return;

        String beforeMethod = hookMethod.beforeMethod();
        String afterMethod = hookMethod.afterMethod();

        if (Preconditions.isNotBlank(beforeMethod)) {
            try {
                Reflect.on(joinPoint.getTarget()).call(beforeMethod);
            } catch (ReflectException e) {
                e.printStackTrace();
                L.e("no method "+beforeMethod);
            }
        }

        joinPoint.proceed();

        if (Preconditions.isNotBlank(afterMethod)) {
            try {
                Reflect.on(joinPoint.getTarget()).call(afterMethod);
            } catch (ReflectException e) {
                e.printStackTrace();
                L.e("no method "+afterMethod);
            }
        }
    }
}

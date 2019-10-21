package com.example.jeffrey.democustomaop.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class JavaAnnotationProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaAnnotationProcessor.class);

    public void javaIntercept(StackTraceElement element) {
        try {
            Method method = Class.forName(element.getClassName()).getMethod(element.getMethodName());
            if (method.isAnnotationPresent(JavaIntercept.class)) {
                executeJavaIntercept();
            }
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    public void executeJavaIntercept() {
        LOGGER.debug("executeJavaIntercept...");
    }

    public void javaCompositeIntercept(StackTraceElement element) {
        try {
            Method method = Class.forName(element.getClassName()).getMethod(element.getMethodName());
            JavaCompositeIntercept compositeIntercept = method.getAnnotation(JavaCompositeIntercept.class);
            if (compositeIntercept.annotationType().isAnnotationPresent(JavaIntercept.class)) {
                executeJavaCompositeIntercept();
            }
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable.getMessage(), throwable);
        }
    }

    @JavaIntercept
    public void executeJavaCompositeIntercept() {
        this.javaIntercept(Thread.currentThread().getStackTrace()[1]);
        LOGGER.debug("executeJavaCompositeIntercept...");
    }

}

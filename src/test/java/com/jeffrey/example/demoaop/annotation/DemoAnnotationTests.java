package com.jeffrey.example.demoaop.annotation;

import com.jeffrey.example.demoaop.aop.SpringIntercept;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

public class DemoAnnotationTests {

    @Test
    public void verifyJavaIntercept() throws Exception {
        Class demoServiceClass = Class.forName("com.jeffrey.example.demoaop.service.DemoService");
        Method[] methods = demoServiceClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(JavaIntercept.class)) {
                JavaIntercept annotation = method.getAnnotation(JavaIntercept.class);
                Assert.assertEquals("", annotation.value());
            }
        }
    }

    @Test
    public void verifySpringIntercept() throws Exception {
        Class demoServiceClass = Class.forName("com.jeffrey.example.demoaop.service.DemoService");
        Method[] methods = demoServiceClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation annotation = AnnotationUtils.findAnnotation(method, SpringIntercept.class);
            if (annotation != null) {
                Map attributes = AnnotationUtils.getAnnotationAttributes(annotation);
                String value = (String)attributes.get("value");
                Assert.assertEquals("", value);
            }
        }
    }

}

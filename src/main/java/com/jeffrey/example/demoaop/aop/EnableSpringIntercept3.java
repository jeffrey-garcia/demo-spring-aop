package com.jeffrey.example.demoaop.aop;

import com.jeffrey.example.demolib.config.DemoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(DemoConfig.class)
public @interface EnableSpringIntercept3 {
}

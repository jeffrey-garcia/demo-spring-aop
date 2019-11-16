package com.jeffrey.example.demoaop.service;

import com.jeffrey.example.demoaop.annotation.JavaAnnotationProcessor;
import com.jeffrey.example.demoaop.annotation.JavaCompositeIntercept;
import com.jeffrey.example.demoaop.annotation.JavaIntercept;
import com.jeffrey.example.demoaop.aop.SpringChainedIntercept;
import com.jeffrey.example.demoaop.aop.SpringIntercept;
import com.jeffrey.example.demoaop.aop.SpringIntercept2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    JavaAnnotationProcessor annotationProcessor;

    @SpringIntercept
    public void execute0(String test1) {
        LOGGER.debug("execute 0...");
    }

    @SpringChainedIntercept
    public void execute1() {
        LOGGER.debug("execute 1...");
    }

    @JavaIntercept
    public void execute2() {
        annotationProcessor.javaIntercept(Thread.currentThread().getStackTrace()[1]);
        LOGGER.debug("execute 2...");
    }

    @JavaCompositeIntercept
    public void execute3() {
        annotationProcessor.javaCompositeIntercept(Thread.currentThread().getStackTrace()[1]);
        LOGGER.debug("execute 3...");
    }

    @SpringIntercept2(method = "execute5")
    public void execute4(String param) {
        LOGGER.debug("execute 4...");
    }

    public void execute5(String param) {
        LOGGER.debug("execute 5...");
    }

}

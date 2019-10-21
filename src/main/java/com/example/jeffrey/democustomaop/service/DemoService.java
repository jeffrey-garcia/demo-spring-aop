package com.example.jeffrey.democustomaop.service;

import com.example.jeffrey.democustomaop.annotation.JavaAnnotationProcessor;
import com.example.jeffrey.democustomaop.annotation.JavaCompositeIntercept;
import com.example.jeffrey.democustomaop.annotation.JavaIntercept;
import com.example.jeffrey.democustomaop.aop.SpringChainedIntercept;
import com.example.jeffrey.democustomaop.aop.SpringIntercept;
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

}

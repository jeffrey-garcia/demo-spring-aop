package com.example.jeffrey.democustomaop.aop;

import com.example.jeffrey.democustomaop.service.DemoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class SpringInterceptorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringInterceptorAspect.class);

    @Autowired
    DemoService demoService;

    @Around("@annotation(SpringIntercept) && args(message,..)")
    public Object springIntercept(ProceedingJoinPoint joinPoint, String message) throws Throwable {
        executeSpringIntercept();
        Object proceed = joinPoint.proceed();
        return proceed;
    }

    public void executeSpringIntercept() {
        LOGGER.debug("executeSpringIntercept...");
    }

    @Around("@annotation(SpringChainedIntercept)")
    public Object springChainedIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
        executeSpringChainedIntercept();
        Object proceed = joinPoint.proceed();
        return proceed;
    }

    public void executeSpringChainedIntercept() {
        demoService.execute0("test1");
        LOGGER.debug("executeSpringChainedIntercept...");
    }


}

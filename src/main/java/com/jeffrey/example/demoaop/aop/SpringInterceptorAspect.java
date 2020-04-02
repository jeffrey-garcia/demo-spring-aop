package com.jeffrey.example.demoaop.aop;

import com.jeffrey.example.demolib.config.DemoConfig;
import com.jeffrey.example.demoaop.service.DemoService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

@Aspect
public class SpringInterceptorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringInterceptorAspect.class);

    @Autowired
    DemoService demoService;

    @Around("execution(* com.jeffrey.example.demoaop.service.FinalService.execute(..))")
    public Object springIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }


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

    @Around("@annotation(springIntercept2)")
    public Object executeSpringIntercept2(ProceedingJoinPoint joinPoint, SpringIntercept2 springIntercept2) throws Throwable {
        LOGGER.debug("execute spring intercept 2: {}", springIntercept2.method());

        String targetMethodName = springIntercept2.method();

        Object[] args = joinPoint.getArgs();
        Class<?>[] classes = new Class[args.length];
        for (int i=0; i<args.length; i++) {
            classes[i] = args[i].getClass();
        }

        try {
            Method method = joinPoint.getTarget().getClass().getDeclaredMethod(targetMethodName, classes);
            method.invoke(joinPoint.getTarget(), args);

            return joinPoint.proceed(args);

        } catch (Exception e) {
            LOGGER.debug(e.getMessage(), e);
            throw e;
        }
    }

    @Pointcut("@within(SpringIntercept3)")
    public void annotatedClass() {}

    @Before("execution(* *(..)) && (annotatedClass())")
    public void executeSpringIntercept3(JoinPoint joinPoint) {
        LOGGER.debug("execute spring intercept 3: {}", joinPoint);
        DemoConfig.springIntercept3.compareAndSet(false, true);
        LOGGER.debug("AopConfig: {}", DemoConfig.springIntercept3.get());
    }

}

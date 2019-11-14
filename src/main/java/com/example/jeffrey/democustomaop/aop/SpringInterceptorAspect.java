package com.example.jeffrey.democustomaop.aop;

import com.example.jeffrey.democustomaop.service.DemoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.lang.reflect.Method;

@Aspect
public class SpringInterceptorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringInterceptorAspect.class);

    ExpressionParser elParser = new SpelExpressionParser();

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


}

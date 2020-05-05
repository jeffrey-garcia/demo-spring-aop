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
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//@Component
@Aspect
public class SpringInterceptorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringInterceptorAspect.class);

    @Autowired
    DemoService demoService;

    /**
     * boolean execute(String s) is NOT overriden by ParentService.java
     * boolean execute(String s1, String s2) is overriden by ParentService.java
     *
     * With the {@link Pointcut} expression specifying the {@link com.jeffrey.example.demoaop.service.ParentService} class,
     * invoking the following methods in the base code will yields different behavior:
     *
     * <p>
     *  {@link com.jeffrey.example.demoaop.service.ParentService#execute(String)}
     *  - the advice won't be triggered and no interception occur
     * </p>
     *
     * <p>
     *  {@link com.jeffrey.example.demoaop.service.ParentService#execute(String, String)}
     *  - the advice will be triggered and interception will occur
     * </p>
     *
     * <p>
     * With the Pointcut expression specifying the {@link com.jeffrey.example.demoaop.service.Service} class,
     * invoking both methods from the base code will then be guaranteed to trigger the advice and
     * interception will occur
     * </p>
     *
     * <p>
     * See <a href="https://www.eclipse.org/aspectj/doc/released/progguide/semantics-pointcuts.html">HERE:</a>
     * </p>
     *
     * <i>When matching method-execution join points, if the execution pointcut method
     * signature specifies a declaring type, the pointcut will only match methods declared
     * in that type, or methods that override methods declared in or inherited by that type.
     * So the pointcut</i>
     */
    @Around("execution(* com.jeffrey.example.demoaop.service.Service.execute(..)) && !within(is(FinalType))")
    public Object springIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
        // the class of the actual instance of joinPoint
        String joinPointTargetClassName = joinPoint.getTarget().getClass().getTypeName();
        LOGGER.debug("join point target class name: {}", joinPointTargetClassName);

        // the class where the join point is intercepted
        String joinPointClassName = joinPoint.getSignature().getDeclaringTypeName();
        LOGGER.debug("join point class name: {}", joinPointClassName);
        String joinPointMethodName = joinPoint.getSignature().getName();
        LOGGER.debug("join point method name: {}", joinPointMethodName);

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

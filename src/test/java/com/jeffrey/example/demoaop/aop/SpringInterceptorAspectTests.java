package com.jeffrey.example.demoaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpringInterceptorAspectTests {

    @Mock
    ProceedingJoinPoint joinPoint;

    @InjectMocks
    SpringInterceptorAspect interceptorAspect;

    @Before
    public void setup() { }

    @Test
    public void executeSpringInterceptor() throws Throwable {
        interceptorAspect.springIntercept(joinPoint, "test1");
    }

}

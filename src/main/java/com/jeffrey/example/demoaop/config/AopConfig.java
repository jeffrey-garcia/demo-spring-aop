package com.jeffrey.example.demoaop.config;

import com.jeffrey.example.demoaop.aop.SpringInterceptorAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * There are 2 ways to register aspect classes:
 * 1) manually register aspect classes as regular beans, or
 * 2) autodetect them through classpath scanning 
 *
 * If you opt for autodetect through classpath scanning,
 * you are required to add a separate @Component annotation
 * to the aspect class together with @Aspect annotation
 */
//@ComponentScan("com.example.jeffrey.demoaop.aop")
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    /**
     * manually register aspect class as regular bean
     */
    @Bean
    public SpringInterceptorAspect interceptorAspect() {
        return new SpringInterceptorAspect();
    }

}

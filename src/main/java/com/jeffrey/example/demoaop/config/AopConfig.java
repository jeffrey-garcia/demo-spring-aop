package com.jeffrey.example.demoaop.config;

import com.jeffrey.example.demoaop.aop.SpringInterceptorAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
//@ComponentScan("com.example.jeffrey.demoaop")
public class AopConfig {

    @Bean
    public SpringInterceptorAspect interceptorAspect() {
        return new SpringInterceptorAspect();
    }

}

package com.example.jeffrey.democustomaop.config;

import com.example.jeffrey.democustomaop.aop.SpringInterceptorAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.example.jeffrey.democustomaop")
public class AopConfig {

    @Bean
    public SpringInterceptorAspect interceptorAspect() {
        return new SpringInterceptorAspect();
    }

}

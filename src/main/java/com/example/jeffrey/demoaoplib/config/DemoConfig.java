package com.example.jeffrey.demoaoplib.config;

import com.example.jeffrey.democustomaop.aop.SpringIntercept3;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicBoolean;

@SpringIntercept3
@Configuration
public class DemoConfig {

    public static AtomicBoolean springIntercept3 = new AtomicBoolean(false);

    @Bean
    @Qualifier("customObjectBean")
    public Object object() {
        return new Object();
    }

}

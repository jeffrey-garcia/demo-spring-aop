package com.example.jeffrey.democustomaop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DemoSpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAopApplication.class, args);
	}

}

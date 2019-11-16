package com.jeffrey.example.demoaop;

import com.jeffrey.example.demoaop.aop.EnableSpringIntercept3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSpringIntercept3
public class DemoSpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAopApplication.class, args);
	}

}
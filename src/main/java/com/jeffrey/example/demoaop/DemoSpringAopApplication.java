package com.jeffrey.example.demoaop;

import com.jeffrey.example.demoaop.aop.EnableSpringIntercept3;
import com.jeffrey.example.demoaop.service.FinalService;
import com.jeffrey.example.demoaop.service.ParentService;
import com.jeffrey.example.demoaop.service.Service;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
@EnableSpringIntercept3
public class DemoSpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAopApplication.class, args);
	}

	@Autowired
	BeanFactory beanFactory;

	@Autowired
	GenericApplicationContext context;

	@EventListener(ApplicationReadyEvent.class)
	protected void postApplicationStartup() {
		context.registerBean(
				"parentService",
				ParentService.class,
				ParentService::new
		);
		context.registerBean(
				"finalService",
				FinalService.class,
				FinalService::new
		);
	}

}

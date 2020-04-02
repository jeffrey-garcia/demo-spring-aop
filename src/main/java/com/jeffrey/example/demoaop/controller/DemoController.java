package com.jeffrey.example.demoaop.controller;

import com.jeffrey.example.demoaop.service.AbstractService;
import com.jeffrey.example.demoaop.service.DemoService;
import com.jeffrey.example.demoaop.service.FinalService;
import com.jeffrey.example.demoaop.service.Service;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

    Service privateService;

    Service finalService;

    public DemoController(
            @Autowired BeanFactory beanFactory,
            @Autowired GenericApplicationContext context
    ) {
        context.registerBean(
                "finalService",
                FinalService.class,
                FinalService::new
        );

        this.finalService = (Service)beanFactory.getBean("finalService");
        this.privateService = (Service)beanFactory.getBean("privateService");
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        finalService.execute("test execute...");
        return "test";
    }

    @GetMapping("/test1")
    public @ResponseBody String test1() {
        demoService.execute0("test1");
        return "test1";
    }

    @GetMapping("/test2")
    public @ResponseBody String test2() {
        demoService.execute2();
        return "test2";
    }

    @GetMapping("/test4")
    public @ResponseBody String test4() {
        demoService.execute4("test execute 4");
        return "test4";
    }


}

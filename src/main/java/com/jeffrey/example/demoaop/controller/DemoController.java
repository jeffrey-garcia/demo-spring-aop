package com.jeffrey.example.demoaop.controller;

import com.jeffrey.example.demoaop.aop.SpringInterceptorAspect;
import com.jeffrey.example.demoaop.service.DemoService;
import com.jeffrey.example.demoaop.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
public class DemoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoService demoService;

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    SpringInterceptorAspect aspect;

    private Advised dynamicAopProxy(Service serviceBean) {
        Function<Object, Advised> aopProxyCreator = new Function<Object, Advised>() {
            private Advised aopProxy;

            @Override
            public Advised apply(Object bean) {
                if (aopProxy == null) {
                    // create a factory that can generate a proxy for the given target object
                    AspectJProxyFactory factory = new AspectJProxyFactory(bean);

                    // add an existing aspect instances, the type of the object
                    // supplied must be an @AspectJ aspect
                    factory.addAspect(aspect);

                    // now get the proxy object...
                    aopProxy = factory.getProxy();
                }
                return aopProxy;
            }
        };

        return aopProxyCreator.apply(serviceBean);
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        Service parentService = (Service) beanFactory.getBean("parentService");
        dynamicAopProxy(parentService);
        parentService.execute("test parent");
        parentService.execute("test parent ","execute");
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

    @GetMapping("/test5")
    public @ResponseBody String test5() {
        Service privateService = (Service) beanFactory.getBean("privateService");
        privateService.execute("test private execute...");
        return "test5";
    }

    @GetMapping("/test6")
    public @ResponseBody String test6() {
        Service finalService = (Service) beanFactory.getBean("finalService");
        finalService.execute("test final execute...");
        return "test6";
    }
}

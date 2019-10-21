package com.example.jeffrey.democustomaop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAspectJAutoProxy
@ComponentScan("com.example.jeffrey.democustomaop")
public class DemoServiceIT {

    @Autowired
    private DemoService demo;

    @Before
    public void setup() { }

    @Test
    public void execute0Test() {
        demo.execute0("test1");
    }

    @Test
    public void execute1Test() {
        demo.execute1();
    }

    @Test
    public void execute2Test() {
        demo.execute2();
    }

    @Test
    public void execute3Test() {
        demo.execute3();
    }

}

package com.example.jeffrey.democustomaop.controller;

import com.example.jeffrey.democustomaop.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    DemoService demoService;

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

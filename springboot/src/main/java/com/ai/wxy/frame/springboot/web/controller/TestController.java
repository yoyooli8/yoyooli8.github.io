package com.ai.wxy.frame.springboot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.wxy.frame.springboot.web.controller.vo.TestVo;

@RestController
public class TestController{
    @RequestMapping("/")
    public String hello(String name){
        return "Hello " + name;
    }
    @RequestMapping("/hello")
    public String hello2(String name){
        return "Hello ---> " + name;
    }
    @RequestMapping("/getUser")
    public TestVo getUser(String name){
        TestVo user = new TestVo();
        user.setName(name);
        user.setAge(20);
        user.setId(3);
        
        return user;
    }
}

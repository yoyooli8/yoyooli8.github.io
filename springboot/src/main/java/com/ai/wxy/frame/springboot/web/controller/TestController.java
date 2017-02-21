package com.ai.wxy.frame.springboot.web.controller;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.wxy.frame.springboot.services.user.service.mq.IQueueFactory;
import com.ai.wxy.frame.springboot.web.controller.vo.Msg;
import com.ai.wxy.frame.springboot.web.controller.vo.TestVo;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class TestController{
    @Resource(name="springbootMqSend")
    private IQueueFactory queueFactory;
    @Resource(name="springbootMqDirectSend")
    private IQueueFactory directQueueSend;
    
    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping("/login2")
    public String login2(){
        return "login-mui";
    }
    @RequestMapping(value ="/home",method=RequestMethod.GET)
    @ApiOperation(value="测试接口", notes="测试thymeleaf接口详细描述")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
    
    @RequestMapping(value ="/sendMsg",method=RequestMethod.POST)
    @ApiOperation(value="测试发送消息", notes="测试测试发送消息接口详细描述")
    @ResponseBody
    public String sendMsg(String msg){
        queueFactory.sendMsg(msg,"spring-boot-topicRouting.test");
        
        directQueueSend.sendMsg(msg,com.ai.wxy.frame.springboot.services.user.service.mq.AmqpConfig.ROUTINGKEY);
        return "success";
    }
    
    @ApiIgnore//使用该注解忽略这个API
    @CacheEvict(value={"userInfo","userRoleInfo"},allEntries=true)
    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Welcome to Spring Boot demo.";
    }
    
    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping("/hello")
    @ResponseBody
    public String hello2(String name){
        return "Hello ---> " + name;
    }
    
    @RequestMapping(value="/getUser",method=RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value="测试用户接口", notes="测试获取用户接口详细描述")
    public TestVo getUser(String name){
        TestVo user = new TestVo();
        user.setName(name);
        user.setAge(20);
        user.setId(3);
        
        return user;
    }
}

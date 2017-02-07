package com.ai.wxy.frame.springboot.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ai.wxy.frame.springboot.core.caller.ResponseResult;
import com.ai.wxy.frame.springboot.services.domain.User;
import com.ai.wxy.frame.springboot.services.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController{
    @Resource
    private IUserService userService;
//    @Resource(name="springbootMqSend")
//    private IQueueFactory queueSend;
//    @Resource(name="springbootTopicMqSend")
//    private IQueueFactory topicqueueSend;
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ApiOperation(value="测试添加用户接口", notes="测试添加用户接口详细描述")
    public ResponseResult<Integer> addUser(@RequestBody User user){
        int rtn = userService.saveUser(user);
        
//        topicqueueSend.sendMsg("添加用户["+user.getUserName()+"]成功", IQueueFactory.TOPICROUTINGPREFIXKEY+"-addUser");
        return ResponseResult.getSuccess("添加用户成功", rtn);
    }
    @RequestMapping(value="/get/{userId}",method=RequestMethod.GET)
    @ApiOperation(value="测试获取用户接口", notes="测试根据用户ID获取用户接口详细描述")
    public ResponseResult<User> addUser(@PathVariable Integer userId){
         User user = userService.getUserById(userId);
//         queueSend.sendMsg("查询到用户名称："+user.getUserName(), AmqpConfig.TOPICEXCHANGE,"spring-boot-topicRouting.getUser");
         
        return ResponseResult.getSuccess("查询用户成功", user);
    }
}

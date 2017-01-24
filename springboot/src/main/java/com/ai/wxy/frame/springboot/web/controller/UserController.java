package com.ai.wxy.frame.springboot.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.wxy.frame.springboot.core.caller.ResponseResult;
import com.ai.wxy.frame.springboot.services.domain.User;
import com.ai.wxy.frame.springboot.services.service.IUserService;
import com.ai.wxy.frame.springboot.web.controller.vo.UserVo;

@RestController
@RequestMapping("/user")
public class UserController{
    @Resource
    private IUserService userService;
    
    @RequestMapping("/add")
    public ResponseResult<Integer> addUser(UserVo user){
        int rtn = userService.saveUser(user);
        
        return ResponseResult.getSuccess("添加用户成功", rtn);
    }
    @RequestMapping("/get/{userId}")
    public ResponseResult<User> addUser(@PathVariable Integer userId){
         User user = userService.getUserById(userId);
        
        return ResponseResult.getSuccess("查询用户成功", user);
    }
}

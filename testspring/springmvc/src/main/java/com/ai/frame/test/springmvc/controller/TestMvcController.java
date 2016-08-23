package com.ai.frame.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.util.JsonResult;

@Controller
@RequestMapping(value="user")
public class TestMvcController {
//	@RequestMapping(value="login",method=RequestMethod.GET)
//	public String login(){
//		System.out.println("跳转到登录页面.");
//		return "modules/login";
//	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult login(String username,String passwd){
		System.out.println("登录的用户名："+username+",密码："+passwd);
		System.out.println("跳转到登录成功页面.");
		return JsonResult.getSuccess("登录成功", new User(1,username,passwd));
	}
}

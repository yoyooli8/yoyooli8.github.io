package com.ai.frame.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.JsonResult;

@Controller
@RequestMapping(value="/")
public class CommController {
    @RequestMapping(value="test",method=RequestMethod.POST)
    @ResponseBody
    public JsonResult test(@RequestBody InputObject<User> inobj){
        System.out.println("测试post入参.");
        return JsonResult.getSuccess("测试post入参.", inobj);
    }
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String login(){
		System.out.println("跳转到登录页面.");
		return "login";
	}
	@RequestMapping(value="login.do",method=RequestMethod.GET)
    public String logindo(){
        System.out.println("跳转到登录页面.");
        return "login";
    }
	@RequestMapping(value="login2",method=RequestMethod.GET)
    public String login2(){
        System.out.println("跳转到登录页面.");
        return "qq_login";
    }
	@RequestMapping(value="requirehbs",method=RequestMethod.GET)
	public String requirehbs(){
		System.out.println("跳转到require测试页面.");
		return "require-hbs-demo";
	}
	@RequestMapping(value="go2Page/{page}",method=RequestMethod.GET)
	public String go2Page(@PathVariable String page){
		return page;
	}
}

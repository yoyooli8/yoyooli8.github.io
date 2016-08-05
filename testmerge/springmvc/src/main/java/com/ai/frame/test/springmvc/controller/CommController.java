package com.ai.frame.test.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class CommController {
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String login(){
		System.out.println("跳转到登录页面.");
		return "login";
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

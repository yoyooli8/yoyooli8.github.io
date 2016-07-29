package com.ai.springmvc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.IServiceFacade;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.JsonResult;

public class Test {
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring-jdbc.xml","conf/spring-mybatis.xml","conf/spring-beans.xml"});
	    
	    IServiceFacade<User> serviceFacade = context.getBean("serviceFacade", IServiceFacade.class);
	    
	    User usermock = new User();
	    usermock.setUserName("测试用户1");
	    usermock.setUserpwd("abc123");
	    usermock.setAddress("测试用户1测试地址");
	    usermock.setAge(23);
	    usermock.setPhone(13681868055L);
	    usermock.setRealName("王小五");
	    
	    InputObject<User> inobj = new InputObject<User>();
	    inobj.setService("userService");
	    inobj.setMethod("save");
	    inobj.setParam(usermock);
	    
	    JsonResult result = serviceFacade.excute(inobj);
	    System.out.println(result);
	    if(result!=null){
	        System.out.println(result.getRtnCode());
	        System.out.println(result.getRtnMsg());
	    }
	    
	}
}

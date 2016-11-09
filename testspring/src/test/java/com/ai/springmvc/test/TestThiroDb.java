package com.ai.springmvc.test;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.IServiceFacade;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.JsonResult;

public class TestThiroDb{
    private IServiceFacade<User> serviceFacade;
    
    @SuppressWarnings({ "resource", "unchecked" })
    @Before
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring-jdbc.xml","conf/spring-mybatis.xml","conf/spring-beans.xml","conf/spring-shiro.xml"});
        serviceFacade = context.getBean("serviceFacade", IServiceFacade.class);
    }
    
    @org.junit.Test
    public void testLogin(){
        User usermock = new User();
        usermock.setUserName("管理员用户1");
        usermock.setUserpwd("abc123");
        InputObject<User> inobj = new InputObject<User>();
        inobj.setService("userService");
        inobj.setMethod("login");
        inobj.setParam(usermock);
        
        JsonResult result = serviceFacade.excute(inobj);
        Assert.assertNotNull(result);
        System.out.println(result);
        
        System.out.println(result.getRtnCode());
        System.out.println(result.getRtnMsg());
        
        Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode()); 
    }

}

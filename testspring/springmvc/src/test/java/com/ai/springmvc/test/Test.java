package com.ai.springmvc.test;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.exception.TestSetValue;
import com.ai.frame.test.springmvc.facade.IServiceFacade;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.JsonResult;

public class Test {
    private IServiceFacade<User> serviceFacade;
    
    @Before
    @SuppressWarnings("unchecked")
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring-jdbc.xml","conf/spring-mybatis.xml","conf/spring-beans.xml"});
        
        serviceFacade = context.getBean("serviceFacade", IServiceFacade.class);
    }
	
    //@org.junit.Test
    public void testSave() {
	    User usermock = new User();
	    usermock.setUserName("测试用户2");
	    usermock.setUserpwd("abc123");
	    usermock.setAddress("测试用户2测试地址");
	    usermock.setAge(23);
	    usermock.setPhone(13681868055L);
	    usermock.setRealName("王小五");
	    usermock.setSex(1);
	    usermock.setCreateTime(Calendar.getInstance().getTime());
	    usermock.setCreateBy(0);
	    usermock.setUpdateTime(Calendar.getInstance().getTime());
	    usermock.setUpdateBy(0);
	    
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
    @org.junit.Test
    public void testUpdate(){
        User usermock = new User();
        usermock.setUserId(1);
        usermock.setSex(0);
        usermock.setCreateTime(Calendar.getInstance().getTime());
        usermock.setCreateBy(0);
        usermock.setUpdateTime(Calendar.getInstance().getTime());
        usermock.setUpdateBy(0);
        
        InputObject<User> inobj = new InputObject<User>();
        inobj.setService("userService");
        inobj.setMethod("updateNotNull");
        inobj.setParam(usermock);
        
        JsonResult result = serviceFacade.excute(inobj);
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode());
        System.out.println(result.getRtnCode());
        System.out.println(result.getRtnMsg());
    }
    @org.junit.Test
    public void testGetPropValue(){
        InputObject<User> inobj = new InputObject<User>();
        inobj.setService("userService");
        inobj.setMethod("getTestSetValue");
        
        JsonResult result = serviceFacade.excute(inobj);
        Assert.assertNotNull(result);
        Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode());
        Assert.assertNotNull(result.getResult());
        Assert.assertEquals("com.ai.frame.test.springmvc.mybatis.mapper", ((TestSetValue)result.getResult()).getBasePackage());
    }
}

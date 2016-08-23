package com.ai.springmvc.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.exception.TestSetValue;
import com.ai.frame.test.springmvc.facade.IServiceFacade;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.util.JsonResult;

public class Test {
    private IServiceFacade<User> serviceFacade;
    private IServiceFacade<Roles> rolesserviceFacade;
    @Before
    @SuppressWarnings({ "unchecked", "resource" })
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"conf/spring-jdbc.xml","conf/spring-mybatis.xml","conf/spring-beans.xml"});
        
        serviceFacade = context.getBean("serviceFacade", IServiceFacade.class);
        rolesserviceFacade = context.getBean("serviceFacade", IServiceFacade.class);
    }
    @org.junit.Test
    public void testGetUserByUserName(){
        User usermock = new User();
        usermock.setUserName("管理员用户1");
        InputObject<User> inobj = new InputObject<User>();
        inobj.setService("userService");
        inobj.setMethod("getUserByName");
        inobj.setParam(usermock);
        
        JsonResult result = serviceFacade.excute(inobj);
        Assert.assertNotNull(result);
        System.out.println(result);
        
        System.out.println(result.getRtnCode());
        System.out.println(result.getRtnMsg());
        
        Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode());
    }
    @org.junit.Test
    public void testUpdateUserRoles(){
        Roles role1 = new Roles();
        role1.setRoleId(3);
        
        List<Roles> roles = new ArrayList<Roles>();
        roles.add(role1);
        User usermock = new User();
        usermock.setUserId(3);
        usermock.setRoles(roles);
        
        InputObject<User> inobj = new InputObject<User>();
        inobj.setService("userService");
        inobj.setMethod("updateUserRoles");
        inobj.setParam(usermock);
        JsonResult result = serviceFacade.excute(inobj);
        Assert.assertNotNull(result);
        System.out.println(result);
        
        System.out.println(result.getRtnCode());
        System.out.println(result.getRtnMsg());
        
        Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode());
    }
    @org.junit.Test
    public void testSaveRole(){
        Roles role = new Roles();
        role.setRoleName("测试角色2");
        role.setDescript("测试角色2");
        role.setStatus(1);
        
        InputObject<Roles> inobj = new InputObject<Roles>();
        inobj.setService("rolesService");
        inobj.setMethod("save");
        inobj.setParam(role);
        JsonResult result = rolesserviceFacade.excute(inobj);
        Assert.assertNotNull(result);
        System.out.println(result);
        
        System.out.println(result.getRtnCode());
        System.out.println(result.getRtnMsg());
        
        Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode());
    }
    @org.junit.Test
    public void testSave() {
	    User usermock = new User();
	    usermock.setUserName("管理员用户1");
	    usermock.setUserpwd("abc123");
	    usermock.setAddress("测试管理员1测试地址");
	    usermock.setAge(23);
	    usermock.setPhone(13681868055L);
	    usermock.setRealName("上官虹燕");
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
	    Assert.assertNotNull(result);
	    System.out.println(result);
        System.out.println(result.getRtnCode());
        System.out.println(result.getRtnMsg());
	    
	    Assert.assertEquals(JsonResult.SUCCESS, result.getRtnCode());
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

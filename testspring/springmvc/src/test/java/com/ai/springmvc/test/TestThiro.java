package com.ai.springmvc.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;

public class TestThiro{
    private Subject subject;
    
    protected void login(String configFile, String username, String password){
      //获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        org.apache.shiro.mgt.SecurityManager manager = factory.getInstance();
        //得到SecurityManager实例 并绑定给SecurityUtils
        SecurityUtils.setSecurityManager(manager);
        
        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        
        try{
            //登录，即身份验证 
            subject.login(token);
        }catch(AuthenticationException e){
            //身份验证失败 
        }
        
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
    }
    
    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
        subject = null;
    }
    
    @org.junit.Test
    public void testHelloworld(){
        login("classpath:shiro.ini","zhang","123");
        
        //退出
        subject.logout();  
    }
    @org.junit.Test
    public void testIsPermitted(){
        login("classpath:shiro-permitted.ini","zhang","123");
        
        //判断拥有权限：user:create 
        Assert.assertTrue(subject.isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete 
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view 
        Assert.assertFalse(subject.isPermitted("user:view")); 
        
        //断言拥有权限：user:create
        subject.checkPermission("user:create"); 
        //断言拥有权限：user:create
        subject.checkPermissions("user:delete", "user:update"); 
        //断言拥有权限：user:view 失败抛出异常
        //subject.checkPermissions("user:view"); 
        
    }
    @org.junit.Test
    public void testHasRole(){
        login("classpath:shiro-role.ini","zhang","123");
        
        boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
        
        //判断拥有角色：role1
        Assert.assertTrue(subject.hasRole("role2"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        
        //断言拥有角色：role1
        subject.checkRole("role1");
        ///断言拥有角色：role1 and role3 失败抛出异常
//        subject.checkRoles("role1", "role3"); 
        
        //退出
        subject.logout();  
    }
}

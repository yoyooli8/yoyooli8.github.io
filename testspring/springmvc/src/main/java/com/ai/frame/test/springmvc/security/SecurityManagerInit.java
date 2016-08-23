package com.ai.frame.test.springmvc.security;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("securityManagerInit")
public class SecurityManagerInit{
    @Autowired
    @Qualifier(value="userRealm")
    private UserRealm userRealm;
    
    @PostConstruct
    public void postConstruct(){
        SessionsSecurityManager securityManager = new org.apache.shiro.mgt.DefaultSecurityManager();
        securityManager.setRealm(userRealm);
        
        SecurityUtils.setSecurityManager(securityManager);
    }
}

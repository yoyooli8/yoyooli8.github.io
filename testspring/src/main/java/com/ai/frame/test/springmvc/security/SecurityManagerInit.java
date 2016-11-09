package com.ai.frame.test.springmvc.security;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("securityManagerInit")
public class SecurityManagerInit{
    @Autowired
    @Qualifier(value="securityManager")
    private SecurityManager securityManager;
    
    @PostConstruct
    public void init(){
        SecurityUtils.setSecurityManager(securityManager);
    }
}

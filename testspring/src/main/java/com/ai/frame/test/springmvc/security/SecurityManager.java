package com.ai.frame.test.springmvc.security;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component("securityManager")
public class SecurityManager extends DefaultWebSecurityManager{
    @Autowired
    @Qualifier(value="userRealm")
    private UserRealm userRealm;
    @Resource(name="sessionManager")
    private SessionManager sessionManager;
    @Resource(name="cacheManager")
    private CacheManager cacheManager;
    
    @PostConstruct
    public void init(){
        setRealm(userRealm);
        setSessionManager(sessionManager);
        setCacheManager(cacheManager);
    }
}

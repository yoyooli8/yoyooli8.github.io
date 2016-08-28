package com.ai.frame.test.springmvc.security;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.springframework.stereotype.Component;
@Component("sessionValidationScheduler")
public class SessionValidationScheduler extends QuartzSessionValidationScheduler{
    private static final int DEFSESSIONVALIDATIONINTERVAL = 1800000;
    @Resource(name="sessionManager")
    private SessionManager sessionManager;
    
    @PostConstruct
    public void init(){
        setSessionValidationInterval(DEFSESSIONVALIDATIONINTERVAL);
        setSessionManager(sessionManager);
    }
}

package com.ai.frame.test.springmvc.security;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Component;
@Component("sessionManager")
public class SessionManager extends DefaultWebSessionManager{
    private static final int DEFGLOBALSESSIONTIMEOUT = 1800000;
    @Resource(name="sessionDao")
    private ShiroSessionDAO sessionDao;
    @Resource(name="sessionValidationScheduler")
    private SessionValidationScheduler sessionValidationScheduler;
    @Resource(name="sessionCookie")
    private SessionIdCookie sessionCookie;
    
    @PostConstruct
    public void init(){
        setGlobalSessionTimeout(DEFGLOBALSESSIONTIMEOUT);
        
        setDeleteInvalidSessions(true);
        setSessionValidationSchedulerEnabled(true);
        setSessionIdCookieEnabled(true);
        
        setSessionValidationScheduler(sessionValidationScheduler);
        setSessionDAO(sessionDao);
        setSessionIdCookie(sessionCookie);
    }
}

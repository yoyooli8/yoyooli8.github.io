package com.ai.frame.test.springmvc.security;

import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.stereotype.Component;

@Component("sessionCookie")
public class SessionIdCookie extends SimpleCookie{
    private static final int DEFMAXAGE = 180000;
    public SessionIdCookie(){
        super("sid");
        
        setHttpOnly(true);
        setMaxAge(DEFMAXAGE);
    }
}

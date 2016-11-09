package com.ai.frame.test.springmvc.security;

import org.springframework.stereotype.Component;

@Component("formAuthenticationFilter")
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter{
    private static final String PARAM_USERNAME = "username";
    private static final String PARAM_USERPWD  = "passwd";
    private static final String PARAM_LOGINURL = "/index";
    
    public FormAuthenticationFilter(){
        setUsernameParam(PARAM_USERNAME);
        setPasswordParam(PARAM_USERPWD);
        setLoginUrl(PARAM_LOGINURL);
    }
}

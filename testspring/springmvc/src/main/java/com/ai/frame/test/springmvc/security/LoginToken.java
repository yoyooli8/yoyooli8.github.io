package com.ai.frame.test.springmvc.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class LoginToken extends UsernamePasswordToken{
    public LoginToken(final String username, final String password) {
        super(username,password);
    }
}

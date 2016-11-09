package com.ai.frame.test.springmvc.security;

import java.io.Serializable;
import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.springframework.stereotype.Component;

@Component("sessionIdGenerator")
public class SessionIdGenerator extends JavaUuidSessionIdGenerator{
    private static final String PREFIX = "shiro";
    public Serializable generateId(Session session) {
        return PREFIX + UUID.randomUUID().toString().replaceAll("-", "");
    }
}

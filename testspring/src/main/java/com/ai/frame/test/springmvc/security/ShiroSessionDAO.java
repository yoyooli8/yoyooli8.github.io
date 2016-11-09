package com.ai.frame.test.springmvc.security;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.stereotype.Component;
@Component("sessionDao")
public class ShiroSessionDAO extends EnterpriseCacheSessionDAO{
    @Resource(name="sessionIdGenerator")
    private SessionIdGenerator sessionIdGenerator;
    @PostConstruct
    public void init(){
        setSessionIdGenerator(sessionIdGenerator);
        setActiveSessionsCacheName("shiro-activeSessionCache");
    }
}

package com.ai.frame.test.springmvc.security;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.stereotype.Component;
@Component("shiroFilter")
public class ShiroFilter extends ShiroFilterFactoryBean{
    private static final String DEF_LOGINURL = "/index";
    private static final String DEF_UNAUTHORIZEDURL = "/unauthorized.jsp";
    @Resource(name="securityManager")
    private SecurityManager securityManager;
    @Resource(name="formAuthenticationFilter")
    private FormAuthenticationFilter formAuthenticationFilter;
    
    @PostConstruct
    public void init(){
        setSecurityManager(securityManager);
        setLoginUrl(DEF_LOGINURL);
        setUnauthorizedUrl(DEF_UNAUTHORIZEDURL);
        
        setFilters(getCustomFilters());
        setFilterChainDefinitionMap(getUrlFilterChainsMap());
    }
    
    private Map<String,Filter> getCustomFilters(){
        Map<String,Filter> filters = new HashMap<String,Filter>();
        filters.put("authc", formAuthenticationFilter);
        
        return filters;
    }
    
    private Map<String, String> getUrlFilterChainsMap(){
        Map<String, String> urlFilterChains = new HashMap<String, String>();
        //mock
        //urlFilterChains.put("index.jsp", "anon");
        //urlFilterChains.put("unauthorized.jsp", "anon");
        //urlFilterChains.put("login.jsp", "authc");
        //urlFilterChains.put("/index", "authc");
        //urlFilterChains.put("logout", "anon");
        //urlFilterChains.put("/assets/images/**",  "anon");
        //urlFilterChains.put("/assets/scripts/**", "anon");
        //urlFilterChains.put("/assets/css/**",     "anon");
        //urlFilterChains.put("/assets/fonts/**",   "anon");
        
        urlFilterChains.put("/**", "anon");
        
        return urlFilterChains;
    }
}

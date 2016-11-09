package com.ai.frame.test.springmvc.security;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.stereotype.Component;
@Component("cacheManager")
public class CacheManager extends EhCacheManager{
    private static final String DEFEHCACHEPATH = "classpath:ehcache.xml";
    public CacheManager(){
        super();
        
        setCacheManagerConfigFile(DEFEHCACHEPATH);
    }
}

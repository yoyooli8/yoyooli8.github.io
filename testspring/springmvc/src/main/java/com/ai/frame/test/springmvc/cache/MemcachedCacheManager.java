package com.ai.frame.test.springmvc.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import net.rubyeye.xmemcached.MemcachedClient;
public class MemcachedCacheManager extends AbstractCacheManager{
    private boolean transactionAware = false;
    private ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();  
    private Map<String, Integer> expireMap = new HashMap<String, Integer>();  
  
    private MemcachedClient memcachedClient;  
  
    public MemcachedCacheManager(){  
    }  
    
    protected Collection<? extends Cache> loadCaches(){  
        Collection<Cache> values = cacheMap.values();  
        return values;  
    }  
    public Cache getCache(String name){
        Cache cache = cacheMap.get(name); 
        if (cache == null){  
            Integer expire = expireMap.get(name);  
            if (expire == null){  
                expire = 0;  
                expireMap.put(name, expire);  
            }  
            cache = new SpringMemcachedCache(name, expire.intValue(), memcachedClient);  
            cacheMap.put(name, cache);  
        }  
        return cache;
    }
    public void setMemcachedClient(MemcachedClient memcachedClient){  
        this.memcachedClient = memcachedClient;  
    }  
  
    public void setConfigMap(Map<String, Integer> configMap){  
        this.expireMap = configMap;  
    }  
    
    public void setTransactionAware(boolean transactionAware) {
        this.transactionAware = transactionAware;
    }
    
    public boolean isTransactionAware() {
        return this.transactionAware;
    }
    
    protected Cache decorateCache(Cache cache) {
        return (isTransactionAware() ? new TransactionAwareCacheDecorator(cache) : cache);
    }
}

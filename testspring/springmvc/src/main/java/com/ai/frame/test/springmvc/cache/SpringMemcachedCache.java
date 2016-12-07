package com.ai.frame.test.springmvc.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import com.ai.frame.test.springmvc.util.ClassUtil;

import net.rubyeye.xmemcached.MemcachedClient;

public class SpringMemcachedCache implements Cache{
    private final String name;  
    private final MemcachedClient memcachedClient;  
    private final MemCache memCache;
    
    public SpringMemcachedCache(String name, int expire, MemcachedClient memcachedClient){  
        this.name = name;  
        this.memcachedClient = memcachedClient;   
        this.memCache = new MemCache(name, expire, memcachedClient);  
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public Object getNativeCache(){
        return this.memcachedClient;
    }

    @Override
    public ValueWrapper get(Object key){
        ValueWrapper wrapper = null;  
        Object value = memCache.get(key.toString());  
        if (value != null){  
            wrapper = new SimpleValueWrapper(value);  
        }  
        return wrapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key,Class<T> type){
        Object value = memCache.get(key.toString()); 
        if(type.isAssignableFrom(value.getClass())){
            return (T)value;
        }else{
            T rtnobj = ClassUtil.newInstance(type);
            ClassUtil.copyProperties(rtnobj, value);
            
            return rtnobj;
        }
    }

    @Override
    public void put(Object key,Object value){
        memCache.put(key.toString(), value);
    }

    @Override
    public void evict(Object key){
        memCache.delete(key.toString());         
    }

    @Override
    public void clear(){
        memCache.clear();  
    } 
    
}

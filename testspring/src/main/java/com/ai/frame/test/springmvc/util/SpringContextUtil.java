package com.ai.frame.test.springmvc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value="springContextUtil")
public class SpringContextUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext; //Spring应用上下文环境
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException{
        applicationContext = context;
    }
    
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    
    public static <T>T getBean(String name,Class<T> requiredType){
        return applicationContext.getBean(name, requiredType);
    }
}

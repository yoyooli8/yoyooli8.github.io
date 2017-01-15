package com.test.web.vo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextHelp implements ApplicationContextAware{
    private ApplicationContext context;
    public <T>T getBean(Class<T> requiredType){
        return context.getBean(requiredType);
    }
    public <T>T getBean(String beanName,Class<T> requiredType){
        return context.getBean(beanName,requiredType);
    }
    public Object getBean(String beanName){
        return context.getBean(beanName);
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        context = applicationContext;
    }
}

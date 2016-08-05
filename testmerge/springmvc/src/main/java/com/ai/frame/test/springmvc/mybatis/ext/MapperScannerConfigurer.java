package com.ai.frame.test.springmvc.mybatis.ext;

import static org.springframework.util.Assert.notNull;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MapperScannerConfigurer extends tk.mybatis.spring.mapper.MapperScannerConfigurer implements ApplicationContextAware{
    private String basePackage;

    public void setBasePackage(String basePackage){
        this.basePackage = basePackage;
    }
    private ApplicationContext applicationContext;
    
    public void afterPropertiesSet() throws Exception {
        notNull(this.basePackage, "Property 'basePackage' is required");
        Map<String, PropertyResourceConfigurer> prcs = applicationContext.getBeansOfType(PropertyResourceConfigurer.class);
        PropertyResourceConfigurer conf = prcs.get("org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#0");
        PropertyPlaceholderConfigurer prop = (PropertyPlaceholderConfigurer)conf;
        super.setBasePackage(prop.toString());
        
        super.afterPropertiesSet();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        this.applicationContext = applicationContext;
        
        super.setApplicationContext(applicationContext);
    }
}

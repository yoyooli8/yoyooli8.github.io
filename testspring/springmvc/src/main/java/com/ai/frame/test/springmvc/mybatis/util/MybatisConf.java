package com.ai.frame.test.springmvc.mybatis.util;

import static org.springframework.util.Assert.notNull;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.util.StringUtils;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

public class MybatisConf{
    private static final String DELIMITER = ",";
    private String basePackage;
    private String pageHelperProperties;
    private Properties properties;
    private MapperScannerConfigurer configurer;
    private SqlSessionFactoryBean sqlSessionFactory;
    
    public String getBasePackage(){
        return basePackage;
    }
    public void setBasePackage(String basePackage){
        this.basePackage = basePackage;
    }
    public String getPageHelperProperties(){
        return pageHelperProperties;
    }
    public void setPageHelperProperties(String pageHelperProperties){
        this.pageHelperProperties = pageHelperProperties;
    }
    public Properties getProperties(){
        return properties;
    }
    
    public void setProperties(Properties properties){
        this.properties = properties;
    }
    public void setConfigurer(MapperScannerConfigurer configurer){
        this.configurer = configurer;
    }
    public void setSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    public void initBean() throws Exception{
        notNull(this.basePackage, "Property 'basePackage' is required");
        notNull(this.pageHelperProperties, "Property 'pageHelperProperties' is required");
        notNull(this.configurer, "Property 'configurer' is required");
        notNull(this.sqlSessionFactory, "Property 'sqlSessionFactory' is required");
        
        Properties properties = StringUtils.splitArrayElementsIntoProperties(this.pageHelperProperties.split(DELIMITER), DELIMITER);
        this.properties = properties;
        
        configurer.setBasePackage(basePackage);
        
        com.github.pagehelper.PageHelper pageHelperPlugin = new com.github.pagehelper.PageHelper();
        pageHelperPlugin.setProperties(properties);
        setPageHelperPlugins(pageHelperPlugin);
        
    }
    
    public void setPageHelperPlugins(Interceptor... plugins) {
        sqlSessionFactory.setPlugins(plugins);
    }
}

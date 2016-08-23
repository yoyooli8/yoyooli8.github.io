package com.ai.frame.test.springmvc.mybatis.ext;

import static org.springframework.util.Assert.notNull;

import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.util.StringUtils;

import com.ai.frame.test.springmvc.exception.TestSetValue;

public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean{
    private static final String DELIMITER = ",";
    private TestSetValue conf;
    
    public void afterPropertiesSet() throws Exception {
        notNull(this.conf, "Property 'conf' is required");
        
        Properties properties = StringUtils.splitArrayElementsIntoProperties(conf.getPageHelperProperties().split(DELIMITER), DELIMITER);
        
        com.github.pagehelper.PageHelper pageHelperPlugin = new com.github.pagehelper.PageHelper();
        pageHelperPlugin.setProperties(properties);
        setPageHelperPlugins(pageHelperPlugin);
        
        super.afterPropertiesSet();
    }
    
    public void setPageHelperPlugins(Interceptor... plugins) {
        super.setPlugins(plugins);
    }

    public void setConf(TestSetValue conf){
        this.conf = conf;
    }
    
}

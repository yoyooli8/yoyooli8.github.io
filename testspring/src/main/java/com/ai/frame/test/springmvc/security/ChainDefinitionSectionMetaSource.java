package com.ai.frame.test.springmvc.security;

import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;

import com.ai.frame.test.springmvc.bo.Menus;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.ImenuService;
import com.github.pagehelper.StringUtil;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{
    private ImenuService menuservice;
    private String filterChainDefinitions;
    private Integer domain;
    private Integer status;
    /** 
     * 默认premission字符串 
     */  
    public static final String PREMISSION_STRING="perms[\"{0}\"]";
    @Override
    public Section getObject() throws Exception{
        InputObject<Menus> inobj = new InputObject<Menus>();
        Menus menu = new Menus();
        menu.setStatus(status);
        menu.setDomain(domain);
        
        List<Menus> menus = menuservice.getAllMenus(inobj);
        Ini ini = new Ini();
        //加载默认的url 
        ini.load(filterChainDefinitions); 
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接 
        if(menus!=null){
            for(Menus pmenu:menus){
                if(!StringUtil.isEmpty(pmenu.getMenuUrl()) && pmenu.getPerms().size()>0){
                    
                }
            }
        }
        
        return null;
    }

    /** 
     * 通过filterChainDefinitions对默认的url过滤定义 
     *  
     * @param filterChainDefinitions 默认的url过滤定义 
     */  
    public void setFilterChainDefinitions(String filterChainDefinitions) {  
        this.filterChainDefinitions = filterChainDefinitions;  
    }  
  
    public Class<?> getObjectType() {  
        return this.getClass();  
    }  
  
    public boolean isSingleton() {  
        return false;  
    }  
}

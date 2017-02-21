package com.ai.wxy.frame.springboot.services.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;
import com.ai.wxy.frame.springboot.services.cms.dao.TemplateDaoI;
import com.ai.wxy.frame.springboot.services.cms.service.TemplateServiceI;
@Service
public class TemplateService implements TemplateServiceI{
    @Autowired
    private TemplateDaoI tplDao;
    public String saveTemplate(Template template){
        return tplDao.saveTemplate(template);
    }
    
    public Template getTemplateById(String uuid){
        return tplDao.getTemplateById(uuid);
    }
    
    public String upTemplateProps(Template template){
        return tplDao.upTemplateProps(template);
    }
    public List<Template> getTemplateProp(String propName){
        TemplateProp prop = new TemplateProp();
        prop.setName(propName);
        
        return tplDao.getTemplateProp(prop);
    }
    
    public boolean updTemplateProp(String uuid,TemplateProp tplProp){
        return tplDao.updTemplateProp(uuid, tplProp);
    }
}

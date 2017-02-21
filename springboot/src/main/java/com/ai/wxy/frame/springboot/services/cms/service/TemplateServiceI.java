package com.ai.wxy.frame.springboot.services.cms.service;

import java.util.List;

import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;

public interface TemplateServiceI{
    public String saveTemplate(Template template);
    public Template getTemplateById(String uuid);
    public String upTemplateProps(Template template);
    
    public List<Template> getTemplateProp(String propName);
    public boolean updTemplateProp(String uuid,TemplateProp tplProp);
}

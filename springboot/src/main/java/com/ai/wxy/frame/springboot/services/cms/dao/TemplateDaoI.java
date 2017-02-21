package com.ai.wxy.frame.springboot.services.cms.dao;

import java.util.List;

import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;

public interface TemplateDaoI{
    /**
      * 添加模板
      * @brief   添加模板
      * @details 添加模板
      * @param template
      * @return
      * @exception 
      * @see 
      * @author wangxiangyang
      * @date 2017年2月13日 下午4:39:03
      * @note wangxiangyang@ 2017年2月13日添加了此方法
     */
    public String saveTemplate(Template template);
    /**
     * 获取模板
     * @brief   获取模板
     * @details 获取模板
     * @param template
     * @return
     * @exception 
     * @see 
     * @author wangxiangyang
     * @date 2017年2月13日 下午4:39:03
     * @note wangxiangyang@ 2017年2月13日添加了此方法
    */
    public Template getTemplateById(String uuid);
    /**
     * 更新模板整个属性
     * @brief   更新模板整个属性
     * @details 更新模板整个属性
     * @param template
     * @return
     * @exception 
     * @see 
     * @author wangxiangyang
     * @date 2017年2月13日 下午4:39:03
     * @note wangxiangyang@ 2017年2月13日添加了此方法
    */
    public String upTemplateProps(Template template);
    /**
     * 根据属性名称获取模板列表
     * @brief   根据属性名称获取模板列表
     * @details 根据属性名称获取模板列表
     * @param template
     * @return
     * @exception 
     * @see 
     * @author wangxiangyang
     * @date 2017年2月13日 下午4:39:03
     * @note wangxiangyang@ 2017年2月13日添加了此方法
    */
    public List<Template> getTemplateProp(TemplateProp tplProp);
    /**
     * 根据属性名称更新模板某个属性的值,如果不存在则添加属性到模板中
     * @brief   根据属性名称更新模板某个属性的值,如果不存在则添加属性到模板中
     * @details 根据属性名称更新模板某个属性的值,如果不存在则添加属性到模板中
     * @param template
     * @return
     * @exception 
     * @see 
     * @author wangxiangyang
     * @date 2017年2月13日 下午4:39:03
     * @note wangxiangyang@ 2017年2月13日添加了此方法
    */
    public boolean updTemplateProp(String uuid,TemplateProp tplProp);
}

package com.ai.wxy.frame.springboot.services.cms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ai.wxy.frame.springboot.services.cms.UpdateUtil;
import com.ai.wxy.frame.springboot.services.cms.beans.Template;
import com.ai.wxy.frame.springboot.services.cms.beans.TemplateProp;
import com.ai.wxy.frame.springboot.services.cms.dao.TemplateDaoI;
import com.ai.wxy.frame.springboot.services.cms.repository.TemplateRepository;
import com.mongodb.WriteResult;
@Repository
public class TemplateDao implements TemplateDaoI{
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public String saveTemplate(Template template){
        Template savedTpl = templateRepository.save(template);
        
        return savedTpl.getUuId();
    }
    
    public Template getTemplateById(String uuid){
        return templateRepository.findByUuId(uuid);
    }
    public boolean updTemplateProp(String uuid,TemplateProp tplProp){
        Template oldTpl = getTemplateById(uuid);
        if(oldTpl == null){
            return Boolean.FALSE;
        }
        
        Query query = new Query();
        query.addCriteria(Criteria.where("uuId").is(uuid).and("tplprops.name").is(tplProp.getName()));
        List<Template> tpls = mongoTemplate.find(query, Template.class);
        if(tpls==null || tpls.size() == 0){
            Query query1 = new Query();
            query1.addCriteria(Criteria.where("uuId").is(uuid));
            
            Update update = new Update();
            update.push("tplprops", tplProp);
            WriteResult upResult = mongoTemplate.upsert(query1, update, Template.class);
            return upResult.getN() > 0;
        }else{
            Update update = UpdateUtil.entity2Update(tplProp,"tplprops.$.");
            
            WriteResult upResult = mongoTemplate.upsert(query, update, Template.class);
            
            return upResult.getN() > 0;
        }
    }
    public List<Template> getTemplateProp(TemplateProp tplProp){
        Query query = new Query();
        query.addCriteria(Criteria.where("tplprops.name").is(tplProp.getName()));
        
        return mongoTemplate.find(query, Template.class);
    }
    public String upTemplateProps(Template template){
        String tplId = template.getUuId();
        
        Template oldTpl = getTemplateById(tplId);
        if(oldTpl == null){
            return saveTemplate(template);
        }else{
            WriteResult upResult = mongoTemplate.updateFirst(new Query(Criteria.where("uuId").is(tplId)), Update.update("tplprops", template.getTplprops()), Template.class);
            return tplId + "_" + upResult.getN();
        }
    }
}

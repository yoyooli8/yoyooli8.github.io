package com.ai.wxy.frame.springboot.services.cms.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ai.wxy.frame.springboot.services.ContentBaseProp;
import com.ai.wxy.frame.springboot.services.cms.UpdateUtil;
import com.ai.wxy.frame.springboot.services.cms.beans.ContentNode;
import com.ai.wxy.frame.springboot.services.cms.dao.ContentNodeDaoI;
import com.mongodb.WriteResult;
@Repository
public class ContentNodeDao implements ContentNodeDaoI{
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public String saveContentNode(ContentNode content){
        Map<String,Object> data = content.getContentMap();
        mongoTemplate.save(data, ContentNode.class.getSimpleName());
        
        return data.get(ContentBaseProp.PROP_CONTENTID.getName()).toString();
    }
    
    public String updateContentNode(ContentNode content){
        String uuid = content.getUuId();
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(uuid));
        
        WriteResult upResult = mongoTemplate.updateFirst(query, UpdateUtil.map2Update(content.getContentMap(),""), ContentNode.class.getSimpleName());
        
        return uuid + "_" + upResult.getN();
    }
}

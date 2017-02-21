package com.ai.wxy.frame.springboot.services.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.wxy.frame.springboot.services.cms.beans.ContentNode;
import com.ai.wxy.frame.springboot.services.cms.dao.ContentNodeDaoI;
import com.ai.wxy.frame.springboot.services.cms.service.ContentNodeServiceI;
@Service
public class ContentNodeService implements ContentNodeServiceI{
    @Autowired
    private ContentNodeDaoI contentNodeDao;
    
    public String saveContentNode(ContentNode content){
        return contentNodeDao.saveContentNode(content);
    }
    
    public String updateContentNode(ContentNode content){
        return contentNodeDao.updateContentNode(content);
    }
}

package com.ai.wxy.frame.springboot.services.cms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.ai.wxy.frame.springboot.services.cms.UpdateUtil;
import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;
import com.ai.wxy.frame.springboot.services.cms.dao.FolderNodeDaoI;
import com.ai.wxy.frame.springboot.services.cms.repository.FolderNodeRepository;
import com.mongodb.WriteResult;
@Repository
public class FolderNodeDao implements FolderNodeDaoI{
    @Autowired
    private FolderNodeRepository folderRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public String saveFolderNode(FolderNode folder){
        FolderNode savedFolder = folderRepository.save(folder);
        
        return savedFolder.getUuId();
    }
    
    public FolderNode getFolderNodeById(String uuid){
        return folderRepository.findByUuId(uuid);
    }
    
    public List<FolderNode> getFolderNodeByName(String name){
        return folderRepository.findByName(name);
    }
    
    public boolean updFolder(FolderNode folder){
        String folderId = folder.getUuId();
        FolderNode oldFolder = getFolderNodeById(folderId);
        if(oldFolder == null){
            return false;
        }else{
            Query query = new Query();
            query.addCriteria(Criteria.where("uuId").is(folderId));
            
            Update update = UpdateUtil.entity2Update(folder,"");
            
            WriteResult upResult = mongoTemplate.updateFirst(query, update, FolderNode.class);
            return upResult.getN() > 0;
        }
    }
}

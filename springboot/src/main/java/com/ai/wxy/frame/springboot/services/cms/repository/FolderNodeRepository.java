package com.ai.wxy.frame.springboot.services.cms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;

public interface FolderNodeRepository extends MongoRepository<FolderNode, String>{
    public FolderNode findByUuId(String uuId);
    public List<FolderNode> findByName(String name);
}

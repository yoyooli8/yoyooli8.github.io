package com.ai.wxy.frame.springboot.services.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;
import com.ai.wxy.frame.springboot.services.cms.dao.FolderNodeDaoI;
import com.ai.wxy.frame.springboot.services.cms.service.FolderNodeServiceI;
@Service
public class FolderNodeService implements FolderNodeServiceI{
    @Autowired
    private FolderNodeDaoI folderDao;
    
    public String saveFolderNode(FolderNode folder){
        return folderDao.saveFolderNode(folder);
    }
    
    public FolderNode getFolderNodeById(String uuid){
        return folderDao.getFolderNodeById(uuid);
    }
    
    public List<FolderNode> getFolderNodeByName(String name){
        return folderDao.getFolderNodeByName(name);
    }
    
    public boolean updFolder(FolderNode folder){
        return folderDao.updFolder(folder);
    }
}

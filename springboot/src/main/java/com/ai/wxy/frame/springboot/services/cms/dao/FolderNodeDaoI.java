package com.ai.wxy.frame.springboot.services.cms.dao;

import java.util.List;

import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;

public interface FolderNodeDaoI{
    String saveFolderNode(FolderNode folder);
    FolderNode getFolderNodeById(String uuid);
    List<FolderNode> getFolderNodeByName(String name);
    boolean updFolder(FolderNode folder);
}

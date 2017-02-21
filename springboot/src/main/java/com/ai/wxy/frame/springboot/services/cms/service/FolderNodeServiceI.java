package com.ai.wxy.frame.springboot.services.cms.service;

import java.util.List;

import com.ai.wxy.frame.springboot.services.cms.beans.FolderNode;

public interface FolderNodeServiceI{
    String saveFolderNode(FolderNode folder);
    FolderNode getFolderNodeById(String uuid);
    List<FolderNode> getFolderNodeByName(String name);
    boolean updFolder(FolderNode folder);
}

package com.ai.wxy.frame.springboot.services.cms.beans;

import com.ai.wxy.frame.springboot.services.cms.CNodeType;

public class FolderNode extends BaseBean{
    private String desc;
    private String folderFlag;
    
    public FolderNode(){
        super();
        setNodeType(CNodeType.FOLDER_NODE.getType());
    }
    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getFolderFlag(){
        return folderFlag;
    }
    public void setFolderFlag(String folderFlag){
        this.folderFlag = folderFlag;
    }
    
}

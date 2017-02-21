package com.ai.wxy.frame.springboot.services.cms;

public enum CNodeType{
    APP_NODE(4,"系统结点"),
    CONTENT_NODE(3,"内容结点"),
    CONTENTPROP_NODE(6,"内容属性"),
    FOLDER_NODE(2,"文件夹结点"),
    TPLPROP_NODE(5,"模板属性"),
    TPL_NODE(1,"模板结点");
    private Integer type;
    private String typeName;
    private CNodeType(Integer type,String typeName){
        this.type     = type;
        this.typeName = typeName;
    }
    public Integer getType(){
        return type;
    }
    public String getTypeName(){
        return typeName;
    }
    
}

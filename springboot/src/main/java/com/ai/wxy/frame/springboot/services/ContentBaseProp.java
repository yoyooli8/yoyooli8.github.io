package com.ai.wxy.frame.springboot.services;

public enum ContentBaseProp{
    PROP_TPLID("tplId","模板ID"),
    PROP_CONTENTID("contentId","数据结点标识"),
    PROP_NAME("name","姓名"),
    PROP_VALIDE("valide","是否有效"),
    PROP_CREATERUID("createrUid","创建人ID"),
    PROP_CREATERUNAME("createrUname","创建人"),
    PROP_CREATETIME("createTime","创建时间"),
    PROP_UPDATEUID("updateUid","修改人ID"),
    PROP_UPDATEUNAME("updateUname","修改人"),
    PROP_UPDATETIME("updateTime","修改时间"),
    PROP_DESC("desc","描述");
    private String name;
    private String lable;
    private ContentBaseProp( String name, String lable){
        this.name  = name;
        this.lable = lable;
    }
    public String getName(){
        return name;
    }
    public String getLable(){
        return lable;
    }
}

package com.ai.wxy.frame.springboot.services.cms;

public enum TPropType{
    DATE_PROP(2,"日期"),
    TIME_PROP(3,"时间"),
    FILE_PROP(4,"文件"),
    PIC_PROP(5,"图片"),
    AREA_PROP(5,"文本域"),
    TXT_PROP(1,"文本");
    private Integer type;
    private String typeName;
    private TPropType(Integer type,String typeName){
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

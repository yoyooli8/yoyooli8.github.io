package com.ai.wxy.frame.springboot.services.cms.beans;

import com.ai.wxy.frame.springboot.services.cms.CNodeType;

public class ContentProp extends BaseBean{
    private String lable;
    private Object propVal;
    
    public ContentProp(){
        super();
        setNodeType(CNodeType.CONTENTPROP_NODE.getType());
    }

    public String getLable(){
        return lable;
    }

    public void setLable(String lable){
        this.lable = lable;
    }

    public Object getPropVal(){
        return propVal;
    }

    public void setPropVal(Object propVal){
        this.propVal = propVal;
    }
    
}

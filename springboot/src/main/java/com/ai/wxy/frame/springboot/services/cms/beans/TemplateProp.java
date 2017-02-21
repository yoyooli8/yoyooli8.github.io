package com.ai.wxy.frame.springboot.services.cms.beans;

import com.ai.wxy.frame.springboot.services.cms.CNodeType;
/**
 * 模板属性结点
 * @author wxy
 * @date   2017-02-12
 */
public class TemplateProp extends BaseBean{
    private String lable;
    private Integer type;
    private Object defautVal;
    private String validates;
    private Boolean isHide;
    private Boolean isReadOnly;
    private Integer order;

    public TemplateProp(){
        super();
        setNodeType(CNodeType.TPLPROP_NODE.getType());
    }

    
    public String getLable(){
        return lable;
    }

    public void setLable(String lable){
        this.lable = lable;
    }

    public Integer getType(){
        return type;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public Object getDefautVal(){
        return defautVal;
    }

    public void setDefautVal(Object defautVal){
        this.defautVal = defautVal;
    }

    public String getValidates(){
        return validates;
    }

    public void setValidates(String validates){
        this.validates = validates;
    }

    public Boolean getIsHide(){
        return isHide;
    }

    public void setIsHide(Boolean isHide){
        this.isHide = isHide;
    }

    public Boolean getIsReadOnly(){
        return isReadOnly;
    }

    public void setIsReadOnly(Boolean isReadOnly){
        this.isReadOnly = isReadOnly;
    }

    public Integer getOrder(){
        return order;
    }
    public void setOrder(Integer order){
        this.order = order;
    }
}

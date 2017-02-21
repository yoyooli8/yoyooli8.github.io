package com.ai.wxy.frame.springboot.services.cms.beans;

import java.util.Date;

import org.springframework.data.annotation.Id;

public abstract class BaseBean{
    @Id
    private String uuId;          //结点ID
    private String puuId;         //父结点ID
    private String name;          //结点名称
    private Integer valide;       //是否有效
    private Integer createrUid;   //创建人Id
    private String createrUname;  //创建人名称
    private Date createTime;      //创建时间
    private Integer updateUid;    //更新人ID  
    private String updateUname;   //更新人名称
    private Date updateTime;      //更新人时间
    private Integer nodeType;     //结点类型
    
    public String getPuuId(){
        return puuId;
    }
    public void setPuuId(String puuId){
        this.puuId = puuId;
    }
    public Integer getNodeType(){
        return nodeType;
    }
    public void setNodeType(Integer nodeType){
        this.nodeType = nodeType;
    }
    public String getUuId(){
        return uuId;
    }
    public void setUuId(String uuId){
        this.uuId = uuId;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getValide(){
        return valide;
    }
    public void setValide(Integer valide){
        this.valide = valide;
    }
    public Integer getCreaterUid(){
        return createrUid;
    }
    public void setCreaterUid(Integer createrUid){
        this.createrUid = createrUid;
    }
    public String getCreaterUname(){
        return createrUname;
    }
    public void setCreaterUname(String createrUname){
        this.createrUname = createrUname;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Integer getUpdateUid(){
        return updateUid;
    }
    public void setUpdateUid(Integer updateUid){
        this.updateUid = updateUid;
    }
    public String getUpdateUname(){
        return updateUname;
    }
    public void setUpdateUname(String updateUname){
        this.updateUname = updateUname;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    
}

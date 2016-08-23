package com.ai.frame.test.springmvc.bo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Permissions{
    //自增长型主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permId;
    private String  permName;
    private String  desc;
    private Integer status;
    public Integer getPermId(){
        return permId;
    }
    public void setPermId(Integer permId){
        this.permId = permId;
    }
    public String getPermName(){
        return permName;
    }
    public void setPermName(String permName){
        this.permName = permName;
    }
    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    
}

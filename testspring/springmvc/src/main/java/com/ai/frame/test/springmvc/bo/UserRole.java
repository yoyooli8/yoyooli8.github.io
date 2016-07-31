package com.ai.frame.test.springmvc.bo;

import javax.persistence.Id;

public class UserRole{
    @Id
    private Integer userId;
    @Id
    private Integer roleId;
    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public Integer getRoleId(){
        return roleId;
    }
    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }
    
}

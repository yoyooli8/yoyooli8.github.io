package com.ai.frame.test.springmvc.bo;

import javax.persistence.Id;

public class RolePerm{
    @Id
    private Integer permId;
    @Id
    private Integer roleId;
    public Integer getPermId(){
        return permId;
    }
    public void setPermId(Integer permId){
        this.permId = permId;
    }
    public Integer getRoleId(){
        return roleId;
    }
    public void setRoleId(Integer roleId){
        this.roleId = roleId;
    }
    
}

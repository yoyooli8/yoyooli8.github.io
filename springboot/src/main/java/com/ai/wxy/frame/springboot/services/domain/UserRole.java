package com.ai.wxy.frame.springboot.services.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="jpa_role")
public class UserRole implements Serializable{
    @Id @GeneratedValue
    private Integer roleId;
    private Integer userId;
    private String roleName;
    
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
    public String getRoleName(){
        return roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }
    
}

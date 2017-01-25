package com.ai.wxy.frame.springboot.security;

import org.springframework.security.core.GrantedAuthority;

import com.ai.wxy.frame.springboot.services.domain.UserRole;

@SuppressWarnings("serial")
public class UserRoles implements GrantedAuthority{
    private Integer roleId;
    private String roleName;
    
    public UserRoles(){}
    public UserRoles(UserRole userRole){
        this.roleId   = userRole.getRoleId();
        this.roleName = userRole.getRoleName();
    }
    @Override
    public String getAuthority(){
        return roleName;
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

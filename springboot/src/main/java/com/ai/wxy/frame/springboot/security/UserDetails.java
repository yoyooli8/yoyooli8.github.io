package com.ai.wxy.frame.springboot.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.ai.wxy.frame.springboot.services.domain.User;

@SuppressWarnings("serial")
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{
    private Integer userId;
    private String userName;
    private String userPwd;
    private boolean enabled;
    private boolean expired;
    private boolean locked;
    private boolean credentialsExpired;
    private List<UserRoles> roles;
    
    public UserDetails(){
    }
    public UserDetails(User user){
        this.userId   = user.getUserId();
        this.userName = user.getUserName();
        this.userPwd  = user.getUserPwd();
        this.enabled  = Boolean.TRUE;
        this.expired  = Boolean.FALSE;
        this.locked   = Boolean.FALSE;
        this.credentialsExpired = Boolean.FALSE;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return roles;
    }

    public List<UserRoles> getRoles(){
        return roles;
    }

    public void setRoles(List<UserRoles> roles){
        this.roles = roles;
    }

    public void setCredentialsExpired(boolean credentialsExpired){
        this.credentialsExpired = credentialsExpired;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserPwd(){
        return userPwd;
    }

    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }

    public boolean isExpired(){
        return expired;
    }

    public void setExpired(boolean expired){
        this.expired = expired;
    }

    public boolean isLocked(){
        return locked;
    }

    public void setLocked(boolean locked){
        this.locked = locked;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    @Override
    public String getPassword(){
        return userPwd;
    }

    @Override
    public String getUsername(){
        return userName;
    }

    @Override
    public boolean isAccountNonExpired(){
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked(){
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled(){
        return enabled;
    }

}

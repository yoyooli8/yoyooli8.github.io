package com.ai.wxy.frame.springboot.services.user.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="jpa_user")
public class User implements Serializable{
    @Id @GeneratedValue
    private Integer userId;
    private String userName;
    private Integer userAge;
    private String userPwd;
    private Date createTime;
    
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
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
    public Integer getUserAge(){
        return userAge;
    }
    public void setUserAge(Integer userAge){
        this.userAge = userAge;
    }
    public String getUserPwd(){
        return userPwd;
    }
    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }
    
}

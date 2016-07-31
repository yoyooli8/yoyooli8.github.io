package com.ai.frame.test.springmvc.bo;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class User {
    //自增长型主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String  userName;
	private String  realName;
	private Integer age;
	private Integer sex;
	private String  address;
	private Long    phone;
	private String  userpwd;
	private String  salt;
	private Date    createTime;
	private Date    updateTime;
	private Integer createBy;
	private Integer updateBy;
	private Integer status;
	@Transient
	private List<Roles> roles;
	public User(){}
	public User(Integer userId,String userName,String userpwd){
		this.userId   = userId;
		this.userName = userName;
		this.userpwd  = userpwd;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRealName(){
        return realName;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    public Integer getCreateBy(){
        return createBy;
    }
    public void setCreateBy(Integer createBy){
        this.createBy = createBy;
    }
    public Integer getUpdateBy(){
        return updateBy;
    }
    public void setUpdateBy(Integer updateBy){
        this.updateBy = updateBy;
    }
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }
    public List<Roles> getRoles(){
        return roles;
    }
    public void setRoles(List<Roles> roles){
        this.roles = roles;
    }
    
}

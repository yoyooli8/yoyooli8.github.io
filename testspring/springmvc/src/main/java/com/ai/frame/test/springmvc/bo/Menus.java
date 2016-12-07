package com.ai.frame.test.springmvc.bo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Menus{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menuId")
    private Integer menuId;
    @Column(name="pmenuId")
    private Integer pMenuId;
    @Column(name="menuName")
    private String menuName;
    @Column(name="menuUrl")
    private String menuUrl;
    @Column(name="domain")
    private Integer domain;
    @Column(name="pmenuIds")
    private String pmenuIds;
    @Column(name="mtype")
    private Integer mtype;
    private Date createTime;
    private Integer createBy;
    private Date updateTime;
    private Integer updateBy;
    private Integer status;
    @Transient
    private List<Permissions> perms;
    
    public List<Permissions> getPerms(){
        return perms;
    }
    public void setPerms(List<Permissions> perms){
        this.perms = perms;
    }
    public Integer getMenuId(){
        return menuId;
    }
    public void setMenuId(Integer menuId){
        this.menuId = menuId;
    }
    public Integer getpMenuId(){
        return pMenuId;
    }
    public void setpMenuId(Integer pMenuId){
        this.pMenuId = pMenuId;
    }
    public String getMenuName(){
        return menuName;
    }
    public void setMenuName(String menuName){
        this.menuName = menuName;
    }
    public String getMenuUrl(){
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl){
        this.menuUrl = menuUrl;
    }
    public Integer getDomain(){
        return domain;
    }
    public void setDomain(Integer domain){
        this.domain = domain;
    }
    public String getPmenuIds(){
        return pmenuIds;
    }
    public void setPmenuIds(String pmenuIds){
        this.pmenuIds = pmenuIds;
    }
    public Integer getMtype(){
        return mtype;
    }
    public void setMtype(Integer mtype){
        this.mtype = mtype;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Integer getCreateBy(){
        return createBy;
    }
    public void setCreateBy(Integer createBy){
        this.createBy = createBy;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
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
    
}

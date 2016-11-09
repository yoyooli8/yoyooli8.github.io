package com.ai.frame.test.springmvc.bo;

import javax.persistence.Column;
import javax.persistence.Id;

public class MenuPerm{
    @Id
    private Integer permId;
    @Id
    @Column(name="menuId")
    private Integer menuId;
    public Integer getPermId(){
        return permId;
    }
    public void setPermId(Integer permId){
        this.permId = permId;
    }
    public Integer getMenuId(){
        return menuId;
    }
    public void setMenuId(Integer menuId){
        this.menuId = menuId;
    }
    
}

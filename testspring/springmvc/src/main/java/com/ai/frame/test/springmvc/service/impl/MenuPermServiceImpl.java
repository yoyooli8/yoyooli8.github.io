package com.ai.frame.test.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.frame.test.springmvc.bo.MenuPerm;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.mybatis.mapper.MenuPermMapper;
import com.ai.frame.test.springmvc.service.IMenuPermService;

import tk.mybatis.mapper.entity.Example;

public class MenuPermServiceImpl extends BaseService<MenuPerm> implements IMenuPermService{
    public int batchSaves(InputObject<MenuPerm> inobj){
        MenuPermMapper mapper = super.getMapper();
        return mapper.batchSaves(inobj.getParams());
    }
    public List<MenuPerm> getMenuPermByMenuIds(InputObject<MenuPerm> inobj){
        MenuPermMapper mapper = super.getMapper();
        List<MenuPerm> menuPerms = mapper.getMenuPermByMenuIds(inobj.getParams());
        
        return menuPerms;
    }
    public List<MenuPerm> getMenuPermByMenuId(InputObject<MenuPerm> inobj){
        Example example = new Example(MenuPerm.class);
        example.createCriteria().andEqualTo("menuId", inobj.getParam().getMenuId());
        List<MenuPerm> menuPerms = super.selectByExample(example);
        
        return menuPerms;
    }
    @Autowired
    public void setMenuPermMapper(MenuPermMapper mapper){
        this.mapper = mapper;
    }
}

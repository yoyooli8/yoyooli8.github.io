package com.ai.frame.test.springmvc.mybatis.mapper;

import java.util.List;

import com.ai.frame.test.springmvc.bo.MenuPerm;
import com.ai.frame.test.springmvc.mybatis.util.MyMapper;

public interface MenuPermMapper extends MyMapper<MenuPerm>{
    public int batchSaves(List<MenuPerm> userRoles);
    public List<MenuPerm> getMenuPermByMenuIds(List<MenuPerm> menuIds);
}

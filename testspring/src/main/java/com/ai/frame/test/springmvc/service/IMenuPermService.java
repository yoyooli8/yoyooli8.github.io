package com.ai.frame.test.springmvc.service;

import java.util.List;

import com.ai.frame.test.springmvc.bo.MenuPerm;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IMenuPermService{
    public List<MenuPerm> getMenuPermByMenuId(InputObject<MenuPerm> inobj);
    public int batchSaves(InputObject<MenuPerm> inobj);
    public List<MenuPerm> getMenuPermByMenuIds(InputObject<MenuPerm> inobj);
}

package com.ai.frame.test.springmvc.service;

import java.util.List;

import com.ai.frame.test.springmvc.bo.Menus;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface ImenuService{
    public List<Menus> getAllMenus(InputObject<Menus> inobj);
    public Menus getMenuById(InputObject<Menus> inobj);
    public List<Menus> getChildMenusByPid(InputObject<Menus> inobj);
}

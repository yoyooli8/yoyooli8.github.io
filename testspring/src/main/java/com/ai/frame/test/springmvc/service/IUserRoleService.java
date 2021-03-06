package com.ai.frame.test.springmvc.service;

import java.util.List;

import com.ai.frame.test.springmvc.bo.UserRole;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IUserRoleService{
    public int save(InputObject<UserRole> inobj);
    public int batchSaves(InputObject<UserRole> inobj);
    public int deleteByExample(InputObject<UserRole> inobj);
    public List<UserRole> getUserByName(InputObject<UserRole> inobj);
}

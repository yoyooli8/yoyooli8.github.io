package com.ai.frame.test.springmvc.service;

import java.util.List;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IUserService{
    public boolean login(InputObject<User> inobj);
    
    public int save(InputObject<User> inobj);
    public int updateNotNull(InputObject<User> inobj);
    public User getUserByName(InputObject<User> inobj);
    public User getUserByNameWithRoles(InputObject<User> inobj);
    public List<Permissions> getUserPermissionsByUserName(InputObject<User> inobj);
    public int updateUserRoles(InputObject<User> inobj);
}

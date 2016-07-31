package com.ai.frame.test.springmvc.service;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IUserService{
    public int save(InputObject<User> inobj);
    public int updateNotNull(InputObject<User> inobj);
    public User getUserByName(InputObject<User> inobj);
}

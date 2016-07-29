package com.ai.frame.test.springmvc.service;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IUserService{
    public int save(InputObject<User> inobj);
}

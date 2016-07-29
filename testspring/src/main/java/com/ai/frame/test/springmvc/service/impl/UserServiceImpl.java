package com.ai.frame.test.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.IUserService;
@Service(value="userService")
public class UserServiceImpl extends BaseService<User> implements IUserService{
    
    public int save(InputObject<User> inobj){
        return super.save(inobj.getParam());
    }
}

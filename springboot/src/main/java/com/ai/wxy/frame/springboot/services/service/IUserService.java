package com.ai.wxy.frame.springboot.services.service;

import com.ai.wxy.frame.springboot.services.domain.User;

public interface IUserService{
    public int saveUser(User user);
    public User getUserById(Integer userId);
}

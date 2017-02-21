package com.ai.wxy.frame.springboot.services.user.service;

import java.util.List;

import com.ai.wxy.frame.springboot.services.user.domain.User;
import com.ai.wxy.frame.springboot.services.user.domain.UserRole;

public interface IUserService{
    public int saveUser(User user);
    public User getUserById(Integer userId);
    public User getUserByName(String userName);
    public List<UserRole> getUserRolesByUserId(Integer userId);
}

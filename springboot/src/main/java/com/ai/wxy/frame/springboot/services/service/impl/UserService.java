package com.ai.wxy.frame.springboot.services.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.wxy.frame.springboot.services.dao.UserCrudRepository;
import com.ai.wxy.frame.springboot.services.domain.User;
import com.ai.wxy.frame.springboot.services.service.IUserService;
@Service
public class UserService implements IUserService{
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Transactional
    public int saveUser(User user){
        User newUser = userCrudRepository.save(user);
        
        return newUser.getUserId();
    }
    
    public User getUserById(Integer userId){
        return userCrudRepository.findOne(userId);
    }
}

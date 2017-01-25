package com.ai.wxy.frame.springboot.services.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ai.wxy.frame.springboot.services.dao.UserCrudRepository;
import com.ai.wxy.frame.springboot.services.dao.UserRoleCrudRepository;
import com.ai.wxy.frame.springboot.services.domain.User;
import com.ai.wxy.frame.springboot.services.domain.UserRole;
import com.ai.wxy.frame.springboot.services.service.IUserService;
@Service
public class UserService implements IUserService{
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private UserRoleCrudRepository userRoleRepository;
    @Transactional
    public int saveUser(User user){
        User newUser = userCrudRepository.save(user);
        
        return newUser.getUserId();
    }
    
    public User getUserByName(String userName){
        User user = new User();
        user.setUserName(userName);
        Example<User> example = Example.of(user);
        
        return userCrudRepository.findOne(example);
    }
    public User getUserById(Integer userId){
        return userCrudRepository.findOne(userId);
    }
    
    public List<UserRole> getUserRolesByUserId(Integer userId){
        return userRoleRepository.findUserRolesByUserId(userId);
    }
}

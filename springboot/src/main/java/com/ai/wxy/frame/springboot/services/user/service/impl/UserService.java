package com.ai.wxy.frame.springboot.services.user.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ai.wxy.frame.springboot.services.user.dao.UserCrudRepository;
import com.ai.wxy.frame.springboot.services.user.dao.UserRoleCrudRepository;
import com.ai.wxy.frame.springboot.services.user.domain.User;
import com.ai.wxy.frame.springboot.services.user.domain.UserRole;
import com.ai.wxy.frame.springboot.services.user.service.IUserService;
@Service
public class UserService implements IUserService{
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private UserRoleCrudRepository userRoleRepository;
    
    @CacheEvict(value={"userInfo","userRoleInfo"},allEntries=true)
    @Transactional
    public int saveUser(User user){
        User newUser = userCrudRepository.save(user);
        
        return newUser.getUserId();
    }
    
    @Cacheable(value="userInfo") //缓存,这里没有指定key
    public User getUserByName(String userName){
        User user = new User();
        user.setUserName(userName);
        Example<User> example = Example.of(user);
        
        return userCrudRepository.findOne(example);
    }
    
    @Cacheable(value="userInfo") //缓存,这里没有指定key
    public User getUserById(Integer userId){
        System.out.println("============getUserById from DB=================");
        return userCrudRepository.findOne(userId);
    }
    
    @Cacheable(value="userRoleInfo") //缓存,这里没有指定key
    public List<UserRole> getUserRolesByUserId(Integer userId){
        System.out.println("============getUserRolesByUserId from DB=================");
        return userRoleRepository.findUserRolesByUserId(userId);
    }
}

package com.ai.frame.test.springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.mybatis.mapper.UserMapper;

import tk.mybatis.mapper.entity.Example;

@Component("userDao")
public class UserDao extends BaseDao<User>{
    @Cacheable(value="defaultCache", key="#userName")
    public User getUserByName(String userName){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName", userName);
        example.orderBy("userId");
        List<User> users = super.selectByExample(example);
        if(users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;
    }
    
    @Autowired
    public void setUserMapper(UserMapper mapper){
        this.mapper = mapper;
    }
}

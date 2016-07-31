package com.ai.frame.test.springmvc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.bo.UserRole;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.IUserService;
import com.ai.frame.test.springmvc.util.SecurityUtil;

import tk.mybatis.mapper.entity.Example;
@Service(value="userService")
public class UserServiceImpl extends BaseService<User> implements IUserService{
    private final static String SEED = "com.wxy.frame";
    public int save(InputObject<User> inobj){
        User user   = inobj.getParam();
        String salt = SecurityUtil.getRandomStr(SEED);
        user.setSalt(salt);
        
        String pwd   = SecurityUtil.pwdMd5Hash(user.getUserpwd(), user.getUserpwd()+ ":" + salt);
        user.setUserpwd(pwd);
        
        return super.save(user);
    }
    
    public int updateNotNull(InputObject<User> inobj){
        return super.updateNotNull(inobj.getParam());
    }
    public int updateUserRoles(InputObject<User> inobj){
        User user         = inobj.getParam();
        List<Roles> roles = user.getRoles();
        
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo("userId", inobj.getParam().getUserId());
        //删除老的绑定关系
        super.deleteByExample(example);
        
        //添加新的关系数据
        //TODO
        return 0;
    }
    public User getUserByName(InputObject<User> inobj){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName", inobj.getParam().getUserName());
        
        List<User> users = super.selectByExample(example);
        if(users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;
    }
}

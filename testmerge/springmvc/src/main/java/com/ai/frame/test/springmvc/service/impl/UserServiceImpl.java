package com.ai.frame.test.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.bo.UserRole;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.IUserRoleService;
import com.ai.frame.test.springmvc.service.IUserService;
import com.ai.frame.test.springmvc.util.SecurityUtil;

import tk.mybatis.mapper.entity.Example;
@Service(value="userService")
public class UserServiceImpl extends BaseService<User> implements IUserService{
    private final static String SEED = "com.wxy.frame";
    @Autowired
    @Qualifier(value="userRoleService")
    private IUserRoleService userRoleService;
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
        
        InputObject<UserRole> deluserRoleinobj = new InputObject<UserRole>();
        UserRole userrole = new UserRole(user.getUserId(),null);
        deluserRoleinobj.setParam(userrole);
        //删除老的绑定关系
        userRoleService.deleteByExample(deluserRoleinobj);
        
        //添加新的用户角色关系数据
        List<UserRole> userRoles = new ArrayList<UserRole>();
        if(roles!=null){
            for(Roles role:roles){
                UserRole urole = new UserRole(user.getUserId(),role.getRoleId());
                userRoles.add(urole);
            }
        }
        
        InputObject<UserRole> userRoleinobj = new InputObject<UserRole>();
        userRoleinobj.setParams(userRoles);
        return userRoleService.batchSaves(userRoleinobj);
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

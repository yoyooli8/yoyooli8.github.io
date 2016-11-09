package com.ai.frame.test.springmvc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.bo.User;
import com.ai.frame.test.springmvc.bo.UserRole;
import com.ai.frame.test.springmvc.exception.FrameException;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.mybatis.mapper.UserMapper;
import com.ai.frame.test.springmvc.security.LoginToken;
import com.ai.frame.test.springmvc.service.IPermissionsService;
import com.ai.frame.test.springmvc.service.IRolesService;
import com.ai.frame.test.springmvc.service.IUserRoleService;
import com.ai.frame.test.springmvc.service.IUserService;
import com.ai.frame.test.springmvc.util.CollectionUtil;
import com.ai.frame.test.springmvc.util.SecurityUtil;

import tk.mybatis.mapper.entity.Example;
@Service(value="userService")
public class UserServiceImpl extends BaseService<User> implements IUserService{
    private final static String SEED = "com.wxy.frame";
    @Autowired
    @Qualifier(value="userRoleService")
    private IUserRoleService userRoleService;
    @Autowired
    @Qualifier(value="rolesService")
    private IRolesService rolesService;
    @Autowired
    @Qualifier(value="permissionsService")
    private IPermissionsService permissionsService;
    
    public boolean login(InputObject<User> inobj){
        User user   = inobj.getParam();
        LoginToken loginToken = new LoginToken(user.getUserName(),user.getUserpwd());
        Subject subject = SecurityUtils.getSubject();
        try{
            //登录，即身份验证 
            subject.login(loginToken);
        }catch(AuthenticationException e){
            //身份验证失败 
            if(e instanceof ExcessiveAttemptsException){
                throw new FrameException("密码输错次数过多，请明天再来。",e,"login.error");
            }else{
                throw new FrameException("用户名或密码错误",e,"login.error");
            }
        }
        
        return subject.isAuthenticated();
    }
    public int save(InputObject<User> inobj){
        User user   = inobj.getParam();
        String salt = SecurityUtil.getRandomStr(SEED);
        user.setSalt(salt);
        
        String pwd   = SecurityUtil.pwd2dbpwd(user.getUserpwd(),salt);
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
    public List<Permissions> getUserPermissionsByUserName(InputObject<User> inobj){
        User user = getUserByName(inobj);
        //取得用户对应的角色IDS
        UserRole urole = new UserRole();
        urole.setUserId(user.getUserId());
        InputObject<UserRole> uroleInobj = new InputObject<UserRole>();
        uroleInobj.setParam(urole);
        List<UserRole> uroles = userRoleService.getUserByName(uroleInobj);
        
        //获取所有用户的操作权限信息
        Set<Roles> roles = CollectionUtil.list2Set(uroles, "roleId", Roles.class);
        InputObject<Roles> roleInobj = new InputObject<Roles>();
        roleInobj.setParams(new ArrayList<Roles>(roles));
        
        List<Permissions> permissions = permissionsService.getPermissionsByRoleIds(roleInobj);
        return permissions;
    }
    public User getUserByNameWithRoles(InputObject<User> inobj){
        User user = getUserByName(inobj);
        
        //取得用户对应的角色IDS
        UserRole urole = new UserRole();
        urole.setUserId(user.getUserId());
        InputObject<UserRole> uroleInobj = new InputObject<UserRole>();
        uroleInobj.setParam(urole);
        List<UserRole> uroles = userRoleService.getUserByName(uroleInobj);
        
        //获取用户对应的角色信息
        Set<Roles> roles = CollectionUtil.list2Set(uroles, "roleId", Roles.class);
        InputObject<Roles> roleInobj = new InputObject<Roles>();
        roleInobj.setParams(new ArrayList<Roles>(roles));
        List<Roles> userRoles = rolesService.getUserRoleByIds(roleInobj);
        
        user.setRoles(userRoles);
        return user;
    }
    public User getUserByName(InputObject<User> inobj){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName", inobj.getParam().getUserName());
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

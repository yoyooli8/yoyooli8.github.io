package com.ai.wxy.frame.springboot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ai.wxy.frame.springboot.services.user.domain.User;
import com.ai.wxy.frame.springboot.services.user.domain.UserRole;
import com.ai.wxy.frame.springboot.services.user.service.IUserService;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userService.getUserByName(userName);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<UserRole> userRoles = userService.getUserRolesByUserId(user.getUserId());
        List<UserRoles> roles    = toUserRoles(userRoles);
        com.ai.wxy.frame.springboot.security.UserDetails userDetails = new com.ai.wxy.frame.springboot.security.UserDetails(user);
        userDetails.setRoles(roles);
        
        return userDetails;
    }
    private List<UserRoles> toUserRoles(List<UserRole> userRoles){
        List<UserRoles> roles = new ArrayList<UserRoles>();
        if(userRoles!=null){
            for(UserRole userRole:userRoles){
                UserRoles role = new UserRoles(userRole);
                roles.add(role);
            }
        }
        return roles;
    }
}

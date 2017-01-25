package com.ai.wxy.frame.springboot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ai.wxy.frame.springboot.services.domain.User;
import com.ai.wxy.frame.springboot.services.domain.UserRole;
import com.ai.wxy.frame.springboot.services.service.IUserService;

public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userService.getUserByName(userName);
        List<UserRole> userRoles = userService.getUserRolesByUserId(user.getUserId());
        
        com.ai.wxy.frame.springboot.security.UserDetails userDetails = new com.ai.wxy.frame.springboot.security.UserDetails(user);
        
        return null;
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

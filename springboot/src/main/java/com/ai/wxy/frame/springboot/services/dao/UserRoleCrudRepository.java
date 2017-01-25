package com.ai.wxy.frame.springboot.services.dao;

import java.util.List;

import com.ai.wxy.frame.springboot.services.domain.UserRole;

public interface UserRoleCrudRepository extends CustomRepository<UserRole, Integer>{
    public List<UserRole> findUserRolesByUserId(Integer userId);
}

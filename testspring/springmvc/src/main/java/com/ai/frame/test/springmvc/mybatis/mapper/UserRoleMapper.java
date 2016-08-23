package com.ai.frame.test.springmvc.mybatis.mapper;

import java.util.List;

import com.ai.frame.test.springmvc.bo.UserRole;
import com.ai.frame.test.springmvc.mybatis.util.MyMapper;

public interface UserRoleMapper extends MyMapper<UserRole>{
    public int batchSaves(List<UserRole> userRoles);
}

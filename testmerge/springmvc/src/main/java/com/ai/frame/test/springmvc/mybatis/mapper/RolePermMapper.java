package com.ai.frame.test.springmvc.mybatis.mapper;

import java.util.List;

import com.ai.frame.test.springmvc.bo.RolePerm;
import com.ai.frame.test.springmvc.mybatis.util.MyMapper;

public interface RolePermMapper extends MyMapper<RolePerm>{
    public int batchSaves(List<RolePerm> rolePerms);
}

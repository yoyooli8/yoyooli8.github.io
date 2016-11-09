package com.ai.frame.test.springmvc.mybatis.mapper;

import java.util.Collection;
import java.util.List;

import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.mybatis.util.MyMapper;

public interface RolesMapper extends MyMapper<Roles>{
    public List<Roles> getRolesByIds(Collection<Roles> roleIds);
}

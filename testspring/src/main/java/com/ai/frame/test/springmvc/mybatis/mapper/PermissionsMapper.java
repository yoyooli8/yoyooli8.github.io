package com.ai.frame.test.springmvc.mybatis.mapper;

import java.util.List;

import com.ai.frame.test.springmvc.bo.Menus;
import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.mybatis.util.MyMapper;

public interface PermissionsMapper extends MyMapper<Permissions>{
    public int batchSaves(List<Permissions> permissions);
    public List<Permissions> getPermissionsByRoleIds(List<Roles> roleIds);
    public List<Permissions> getPermissionsByMenuIds(List<Menus> menuIds);
}

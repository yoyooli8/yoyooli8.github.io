package com.ai.frame.test.springmvc.service;

import java.util.List;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IPermissionsService{
    public int save(InputObject<Permissions> inobj);
    public int updateNotNull(InputObject<Permissions> inobj);
    public Permissions getUserById(InputObject<Permissions> inobj);
    public List<Permissions> getPermissionsByRoleIds(InputObject<Roles> inobj);
}

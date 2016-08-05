package com.ai.frame.test.springmvc.service;

import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IRolesService{
    public int save(InputObject<Roles> inobj);
    public int updateNotNull(InputObject<Roles> inobj);
    public int updateRolePermissions(InputObject<Roles> inobj);
    public Roles getUserById(InputObject<Roles> inobj);
}

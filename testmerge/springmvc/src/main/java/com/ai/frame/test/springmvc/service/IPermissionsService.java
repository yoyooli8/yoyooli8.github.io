package com.ai.frame.test.springmvc.service;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IPermissionsService{
    public int save(InputObject<Permissions> inobj);
    public int updateNotNull(InputObject<Permissions> inobj);
    public Permissions getUserById(InputObject<Permissions> inobj);
}

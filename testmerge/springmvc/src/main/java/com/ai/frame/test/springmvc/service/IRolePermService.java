package com.ai.frame.test.springmvc.service;

import com.ai.frame.test.springmvc.bo.RolePerm;
import com.ai.frame.test.springmvc.facade.dto.InputObject;

public interface IRolePermService{
    public int save(InputObject<RolePerm> inobj);
    public int batchSaves(InputObject<RolePerm> inobj);
    public int deleteByExample(InputObject<RolePerm> inobj);
}

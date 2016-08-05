package com.ai.frame.test.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.IPermissionsService;
@Service(value="permissionsService")
public class PermissionsServiceImpl extends BaseService<Permissions> implements IPermissionsService{
    public int save(InputObject<Permissions> inobj){
        return super.save(inobj.getParam());
    }
    public int updateNotNull(InputObject<Permissions> inobj){
        return super.updateNotNull(inobj.getParam());
    }
    public Permissions getUserById(InputObject<Permissions> inobj){
        return super.selectByKey(inobj.getParam().getPermId());
    }
    
}

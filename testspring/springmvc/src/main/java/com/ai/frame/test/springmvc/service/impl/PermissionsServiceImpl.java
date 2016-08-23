package com.ai.frame.test.springmvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.mybatis.mapper.PermissionsMapper;
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
    
    public List<Permissions> getPermissionsByRoleIds(InputObject<Roles> inobj){
        PermissionsMapper mapper = getMapper();
        
        return mapper.getPermissionsByRoleIds(inobj.getParams());
    }
    
    @Autowired
    public void setPermissionsMapper(PermissionsMapper mapper){
        this.mapper = mapper;
    }
}

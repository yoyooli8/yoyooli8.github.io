package com.ai.frame.test.springmvc.service.impl;

import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.RolePerm;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.mybatis.mapper.RolePermMapper;
import com.ai.frame.test.springmvc.service.IRolePermService;

import tk.mybatis.mapper.entity.Example;
@Service(value="rolePermService")
public class RolePermServiceImpl extends BaseService<RolePerm> implements IRolePermService{
    public int save(InputObject<RolePerm> inobj){
        return super.save(inobj.getParam());
    }
    
    public int batchSaves(InputObject<RolePerm> inobj){
        RolePermMapper mapper = super.getMapper();
        return mapper.batchSaves(inobj.getParams());
    }
    
    public int deleteByExample(InputObject<RolePerm> inobj){
        Example example = new Example(RolePerm.class);
        example.createCriteria().andEqualTo("roleId", inobj.getParam().getRoleId());
        
        return super.deleteByExample(example);
    }
}

package com.ai.frame.test.springmvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ai.frame.test.springmvc.bo.Permissions;
import com.ai.frame.test.springmvc.bo.RolePerm;
import com.ai.frame.test.springmvc.bo.Roles;
import com.ai.frame.test.springmvc.facade.dto.InputObject;
import com.ai.frame.test.springmvc.service.IRolePermService;
import com.ai.frame.test.springmvc.service.IRolesService;
@Service(value="rolesService")
public class RolesServiceImpl extends BaseService<Roles> implements IRolesService{
    @Autowired
    @Qualifier(value="rolePermService")
    private IRolePermService rolePermService;
    public int save(InputObject<Roles> inobj){
        return super.save(inobj.getParam());
    }
    public int updateNotNull(InputObject<Roles> inobj){
        return super.updateNotNull(inobj.getParam());
    }
    public int updateRolePermissions(InputObject<Roles> inobj){
        Roles role = inobj.getParam();
        List<Permissions> permissions = role.getPermissions();
        
        InputObject<RolePerm> deluRolePermobj = new InputObject<RolePerm>();
        RolePerm rolePerm = new RolePerm();
        rolePerm.setRoleId(role.getRoleId());
        deluRolePermobj.setParam(rolePerm);
        //删除老的绑定关系
        rolePermService.deleteByExample(deluRolePermobj);
        
        //添加新的角色权限关系数据
        List<RolePerm> rolePerms = new ArrayList<RolePerm>();
        if(permissions!=null){
            for(Permissions perm:permissions){
                RolePerm rperm = new RolePerm();
                rperm.setRoleId(role.getRoleId());
                rperm.setPermId(perm.getPermId());
                rolePerms.add(rperm);
            }
        }
        InputObject<RolePerm> rolePermobj = new InputObject<RolePerm>();
        rolePermobj.setParams(rolePerms);
        return rolePermService.batchSaves(rolePermobj);
    }
    public Roles getUserById(InputObject<Roles> inobj){
        return super.selectByKey(inobj.getParam().getRoleId());
    }
}

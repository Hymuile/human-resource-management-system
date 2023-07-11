package org.hr.service;

import org.apache.ibatis.annotations.Update;
import org.hr.mapper.UserRoleMapper;
import org.hr.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    //新增权限
    public void addUserRole(UserRole userRole){
        userRoleMapper.addUserRole(userRole);
    }
}

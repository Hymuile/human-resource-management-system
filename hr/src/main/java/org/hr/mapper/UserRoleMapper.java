package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.UserRole;

@Mapper
public interface UserRoleMapper {
    //新增权限
    public void addUserRole(UserRole userRole);
}

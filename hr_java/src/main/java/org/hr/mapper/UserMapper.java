package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.Role;
import org.hr.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
    List<Role> getUserRoleByUid(String id);
    //新建用户
    int addUser(User user);
    //通过用户名查找用户(精确查找)
    User findUserByUsername(String username);
    //通过用户名查询用户权限
    List<Role> getUserRoleByUsername(String username);
    //重置用户密码
    int resetPassword(User user);
    //删除用户
    public void deleteUser(String username);

    //修改用户密码
    public void updateUserPassword(User user);

}

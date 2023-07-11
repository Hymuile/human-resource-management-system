package org.hr.service;

import com.alibaba.druid.util.StringUtils;
import org.hr.mapper.UserMapper;
import org.hr.model.Role;
import org.hr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.loadUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("帐户不存在");
        }
        user.setRoles(userMapper.getUserRoleByUid(user.getId()));
        return user;
    }

    //通过用户或查找用户（精确查找）
    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    //新增用户
    public int addUser(User user){
        return userMapper.addUser(user);
    }

    //通过用户名查询用户权限
    public List<Role> findRolesByUsername(String username){
        return userMapper.getUserRoleByUsername(username);
    }

    //重置用户密码
    public int resetPassword(User user){
        return userMapper.resetPassword(user);
    }

    //删除用户
    public void deleteUser(String username){
        userMapper.deleteUser(username);
    }

    //修改用户密码
    public void updateUserPassword(User user){
        userMapper.updateUserPassword(user);
    }
}

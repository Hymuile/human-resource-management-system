package org.hr.service;

import org.hr.mapper.UserInfoMapper;
import org.hr.model.UserInfo;
import org.hr.model.UserPost;
import org.hr.model.UserRole;
import org.hr.modelOv.UserOV;
import org.hr.modelOv.UserPostOV;
import org.hr.modelOv.UserSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    //通过用户名查找用户信息（精确查找）
    public UserInfo findUserInfoByUsername(String username){
        return userInfoMapper.getUserByUsername(username);
    }
    //新增用户信息
    public int addUserinfo(UserInfo userInfo){
        return userInfoMapper.addUserInfo(userInfo);
    }
    // 通过用户名查找用户信息（模糊查找）
    public List<UserInfo> findUsersInfoByUsername(UserOV userOV){
        return userInfoMapper.getUsersByUsername(userOV);
    }

    //通过用户名查找用户数量（模糊查找）
    public int findUserNumByUsername(UserOV userOV){
        return userInfoMapper.getUsersNumByUsername(userOV);
    }

    // 变更用户信息
    public int updateUserInfo(UserInfo userInfo){
        return userInfoMapper.updateUserInfo(userInfo);
    }

    //更新员工岗位
    public int modifyUserPost(UserInfo userInfo){
        return userInfoMapper.updateUserInfo(userInfo);
    }
    //通过用户名查找员工及岗位（模糊查询）
    public List<UserPost> getUserPostsByUsername(UserPostOV userPostOV){
        return userInfoMapper.getUserPostsByUsername(userPostOV);
    }
    //通过用户名查找员工及岗位数量（模糊查询）
    public Integer getUserPostNumByUsername(UserPostOV userPostOV){
        return userInfoMapper.getUserPostNumByUsername(userPostOV);
    }

    //查询员工本人岗位
    public UserPost getUserPostByUsername(String username){
        return userInfoMapper.getUserPostByUsername(username);
    }

    //删除用户
    public void deleteUserInfo(String username){
        userInfoMapper.deleteUserInfo(username);
    }

    //修改本人信息
    public int updateUserInfo2(UserInfo userInfo){
        return userInfoMapper.updateUserInfo2(userInfo);
    }

    //更新本人图片地址信息
    public void updateUserPhoto(UserInfo userInfo){
        userInfoMapper.updateUserPhoto(userInfo);
    }

    //查询所有人薪酬
    public List<UserSalary> getUsersSalaryByUsername(UserOV userOV){
        return userInfoMapper.getUsersSalaryByUsername(userOV);
    }

    //查询所有人薪酬数量
    public Integer getUserSalaryNumByUsername(UserOV userOV){
        return userInfoMapper.getUserSalaryNumByUsername(userOV);
    }

    //修改员工薪酬
    public Integer modifyUserSalary(UserSalary userSalary){
        return userInfoMapper.modifyUserSalary(userSalary);
    }

    //查询本人薪酬
    public UserSalary getUserSalary(String username){
        return userInfoMapper.getUserSalary(username);
    }
}

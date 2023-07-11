package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.UserInfo;
import org.hr.modelOv.UserOV;
import org.hr.model.UserPost;
import org.hr.modelOv.UserPostOV;
import org.hr.modelOv.UserSalary;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    //通过用户名查找用户信息（精确查找）
    public UserInfo getUserByUsername(String username);
    //新建用户信息
    public int addUserInfo(UserInfo userInfo);
    //通过用户名查找用户信息（模糊查找）
    public List<UserInfo> getUsersByUsername(UserOV userOV);
    //通过用户名查找用户总数量（模糊查找）
    public Integer getUsersNumByUsername(UserOV userOV);
    // 修改用户信息
    public Integer updateUserInfo(UserInfo userInfo);
    //修改用户岗位
    public Integer modifyUserPost(UserInfo userInfo);
    //查询用户岗位（模糊查询）
    public List<UserPost> getUserPostsByUsername(UserPostOV userPostOV);
    //查询用户岗位数量（模糊查询）
    public int getUserPostNumByUsername(UserPostOV userPostOV);
    //查询本人岗位
    public UserPost getUserPostByUsername(String username);
    // 删除用户
    public void deleteUserInfo(String username);
    // 修改本人信息
    public int updateUserInfo2(UserInfo userInfo);
    //将用户图片地址写入数据库
    public void updateUserPhoto(UserInfo userinfo);
    //查询所有员工薪酬（模糊查询）
    public List<UserSalary> getUsersSalaryByUsername(UserOV userOV);
    //查询所有员工薪酬数量（模糊查询）
    public Integer getUserSalaryNumByUsername(UserOV userOV);
    //修改员工薪酬
    public Integer modifyUserSalary(UserSalary userSalary);
    //查询员工薪酬
    public UserSalary getUserSalary(String username);
}

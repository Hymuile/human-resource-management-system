package org.hr.controller;

import org.hr.mapper.UserMapper;
import org.hr.model.User;
import org.hr.model.UserInfo;
import org.hr.model.UserPost;
import org.hr.model.UserRole;
import org.hr.modelOv.UserOV;
import org.hr.modelOv.UserPostOV;
import org.hr.modelOv.UserSalary;
import org.hr.service.UserInfoService;
import org.hr.service.UserRoleService;
import org.hr.service.UserService;
import org.hr.util.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.CryptoPrimitive;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    AdminUtil adminUtil;

    //新建用户
    @PostMapping("/admin/create_user")
    public Object createUser(@RequestBody  UserInfo userInfo){
        Map<String,Object> map=new HashMap<>();
        User user=new User();
        user.setUsername(userInfo.getUsername());
        //先检查用户是否存在
        if(getUserByUsername(user.getUsername())){
            //用户已存在，不新建，并返回提示
            map.put("state",202);
            map.put("msg","用户已存在");
        }else{
            //用户未存在，新建用户
            //先对用户密码进行加密
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            user.setPassword(encoder.encode("123"));
            int result_1=userService.addUser(user);
            //新增用户信息
            // UserInfo userInfo=new UserInfo();
            userInfo.setUsername(user.getUsername());
            int result_2=userInfoService.addUserinfo(userInfo);
            //新增权限
            User newUser=userService.findUserByUsername(user.getUsername());
            UserRole userRole=new UserRole();
            userRole.setUid(newUser.getId());
            userRole.setRid("f412c01ac38d11eaab09000c29ee2ecd");
            userRoleService.addUserRole(userRole);

            if(result_1 == 1&&result_2==1){
                map.put("state",200);
                map.put("msg","新建用户成功");
            }else{
                map.put("state",500);
                map.put("msg","新增用户失败");
            }
        }
        return map;
    }

    //按用户名查找用户（精确查找）
    @GetMapping("/user/getuserbyusername")
    public Object getUserByUsername_coltroller(){
        Map<String,Object> map=new HashMap<>();
        //获取当前登录的用户名
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        UserInfo userInfo=userInfoService.findUserInfoByUsername(username);
        if(userInfo!=null){
            // 用户存在
            map.put("state",200);
            map.put("msg","查询用户成功");
            map.put("data",userInfo);
            return map;
        }else{
            //用户名不存在
            UserOV userOv=new UserOV();
            map.put("state",202);
            map.put("msg","用户不存在");
            return map;
        }
    }

    //按用户名查找用户（模糊查找）
    @GetMapping("/admin/getusersbyusername")
    public Object getUsersByUsername_coltroller(String username,int current_index,int page_size){
        Map<String,Object> map=new HashMap<>();
        map.put("state",200);
        map.put("msg","查询用户成功");
        UserOV userOV=new UserOV();
        userOV.setUsername(username);
        userOV.setCurrentIndex(current_index);
        userOV.setPageSize(page_size);
        List<UserInfo> usersInfo=userInfoService.findUsersInfoByUsername(userOV);
        Integer usersNum=userInfoService.findUserNumByUsername(userOV);
        map.put("data",usersInfo);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("allnum",usersNum);

        return map;
    }

    //变更用户信息
    @PutMapping("/user/modify_user")
    public Object modifyUser(@RequestBody UserInfo userInfo){
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();

        if(adminUtil.isAdmin(username)||username.equals(userInfo.getUsername())){
            Integer result=userInfoService.updateUserInfo(userInfo);
            if(result == 1){
                map.put("state",200);
                map.put("msg","更新用户信息成功");
            }else{
                map.put("state",500);
                map.put("msg","更新用户信息失败");
            }
        }else{
            map.put("state",202);
            map.put("msg","不允许操作");
        }

        return map;
    }

    //重置用户密码
    @PutMapping("/admin/reset_password")
    public Object resetPassword(@RequestBody User user){
        Map<String,Object> map=new HashMap<>();
        //密码加密
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //写入数据库
        int result=userService.resetPassword(user);
        if(result==1){
            map.put("state",200);
            map.put("msg","重置密码成功");
        }else{
            map.put("state",202);
            map.put("msg","重置密码失败");
        }

        return map;
    }

    //更新员工岗位
    @PutMapping("/admin/modify_user_post")
    public Object modifyUserPost(@RequestBody UserInfo userInfo){
        Map<String,Object> map=new HashMap<>();
        userInfoService.modifyUserPost(userInfo);

        return map;
    }
    //查找员工岗位
    @GetMapping("/admin/get_user_post")
    public Object getUsersPost(String username,Integer current_index,Integer page_size){
        Map<String ,Object> map=new HashMap<>();
        UserPostOV userPostOV=new UserPostOV();
        userPostOV.setUsername(username);
        userPostOV.setCurrent_index(current_index);
        userPostOV.setPage_size(page_size);
        List<UserPost> userPosts=userInfoService.getUserPostsByUsername(userPostOV);
        Integer totalNum=userInfoService.getUserPostNumByUsername(userPostOV);

        map.put("state",200);
        map.put("msg","查询用户岗位成功");
        map.put("data",userPosts);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("total",totalNum);

        return map;
    }

    //查找员工本人岗位
    @GetMapping("/user/get_post")
    public Object getUserPost(){
        Map<String,Object> map=new HashMap<>();
        //从session获取登录的用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        UserPost userPost=userInfoService.getUserPostByUsername(username);
        map.put("state",200);
        map.put("msg","获取员工岗位成功");
        map.put("data",userPost);

        return map;
    }

    //按用户名查找用户（精确查找）
    public boolean getUserByUsername(String username){
        User user=userService.findUserByUsername(username);
        if(user!=null){
            return true;
        }else{
            return false;
        }
    }

    //删除用户
    @PutMapping("/admin/delete_user")
    public Object deleteUser(String username){
        Map<String,Object> map=new HashMap<>();
        userService.deleteUser(username);
        userInfoService.deleteUserInfo(username);
        map.put("state",200);
        map.put("msg","删除用户成功");

        return map;
    }

    //查询个人信息
    @GetMapping("/user/get_userinfo")
    public Object getUserInfo(){
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username=user.getUsername();
        UserInfo userInfo=userInfoService.findUserInfoByUsername(username);
        if(userInfo!=null){
            map.put("state",200);
            map.put("msg","查询个人信息成功");
            map.put("data",userInfo);
        }else{
            map.put("state",202);
            map.put("msg","查询个人信息失败");
        }

        return map;
    }

    //修改个人信息
    @PutMapping("/user/modify_userinfo")
    public Object midifyUserInfo(@RequestBody UserInfo userInfo){
        Map<String,Object> map = new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userInfo.setUsername(user.getUsername());
        int result=userInfoService.updateUserInfo2(userInfo);
        if(result==1){
            map.put("state",200);
            map.put("msg","修改个人信息成功");
        }else{
            map.put("state",202);
            map.put("msg","修改个人信息失败");
        }

        return map;
    }

    //修改用户密码
    @PutMapping("/user/modify_user_password")
    public Object modifyUserPassord(String oldPassword,String newPassword){
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user=userService.findUserByUsername(user.getUsername());

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        boolean match=encoder.matches(oldPassword,user.getPassword());
        if(match){
            //原密码正确，修改密码
            user.setPassword(encoder.encode(newPassword));
            userService.updateUserPassword(user);
            map.put("state",200);
            map.put("msg","修改密码成功");
        }else{
            map.put("state",202);
            map.put("msg","旧密码不正确");
        }

        return map;
    }

    //查询所有人薪酬
    @GetMapping("/admin/get_alluser_salary")
    public Object getUsersSalaryByUsername(String username,Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        UserOV userOV=new UserOV();
        userOV.setUsername(username);
        userOV.setCurrentIndex(current_index);
        userOV.setPageSize(page_size);
        List<UserSalary> userSalaries=userInfoService.getUsersSalaryByUsername(userOV);
        Integer total=userInfoService.getUserSalaryNumByUsername(userOV);
        map.put("state",200);
        map.put("msg","查询薪酬成功");
        map.put("data",userSalaries);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("total",total);

        return map;
    }

    //修改员工薪酬
    @PutMapping("/admin/modify_user_salary")
    public Object modifyUserSalary(@RequestBody UserSalary userSalary){
        Map<String,Object> map=new HashMap<>();
        Integer result = userInfoService.modifyUserSalary(userSalary);
        if(result == 1){
            map.put("state",200);
            map.put("msg","修改员工薪酬成功");
        }else{
            map.put("state",202);
            map.put("msg","修改员工薪酬失败");
        }
        return map;
    }

    //获取本人薪酬
    @GetMapping("/user/get_user_salary")
    public Object getUserSalary(){
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserSalary userSalary=userInfoService.getUserSalary(user.getUsername());
        map.put("state",200);
        map.put("msg","获取本人薪酬成功");
        map.put("data",userSalary);

        return map;
    }

}

package org.hr.util;

import org.hr.model.Role;
import org.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminUtil {
    @Autowired
    UserService userService;

    //通过用户名返回用户是否为admin用户
    public boolean isAdmin(String username){
        List<Role> roles=userService.findRolesByUsername(username);
        for(Role role:roles){
            //System.out.println("===role:"+role.getName());
            if(role.getName().endsWith("admin")){
                return true;
            }
        }
        return false;
    }
}

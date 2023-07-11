package org.hr.controller;

import org.hr.model.User;
import org.hr.util.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestColtroller {

    @Autowired
    AdminUtil adminUtil;
    //
    @GetMapping("/admin/hello")
    public String admin(){
        if(adminUtil.isAdmin("admin"))
            return "hello amdin";
        else
            return "hello not admin";
    }

    @GetMapping("/user/hello")
    public String user(){
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(adminUtil.isAdmin(user.getUsername()))
            return "hello amdin";
        else
            return "hello not admin";
    }
}

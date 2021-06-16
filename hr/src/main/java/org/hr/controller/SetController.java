package org.hr.controller;

import org.hr.model.Menu;
import org.hr.service.MenuService;
import org.hr.util.AdminUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SetController {

    @Autowired
    MenuService menuService;
    @Autowired
    AdminUtil adminUtil;

    //获取菜单
    @GetMapping("/user/getmenu")
    public  Object getMenu(HttpSession session){
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
        Map<String,Object> map=new HashMap<>();
        boolean isAdmin=false;
        if(adminUtil.isAdmin(username))
            isAdmin = true;
        else
            isAdmin = false;
        // 查询所有一级菜单
        List<Menu> menu1=menuService.getMenu_1();
        for(Menu menu:menu1){
            Menu menu_in=new Menu();
            menu_in.setSup_id(menu.getId());
            if(isAdmin)
                menu_in.setRights("/admin");
            else
                menu_in.setRights("/user");
            List<Menu> menu2=menuService.getMenu_2(menu_in);
            menu.setChildren(menu2);
        }
        //返回的map
        map.put("state",200);
        map.put("msg","获取菜单成功");
        map.put("data",menu1);

        return map;
    }
}

package org.hr.service;

import org.apache.ibatis.annotations.Mapper;
import org.hr.mapper.MenuMapper;
import org.hr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    //查询所有一级菜单
    public List<Menu> getMenu_1(){
        return menuMapper.getMenu_1();
    }

    //查询所有二级菜单
    public List<Menu> getMenu_2(Menu menu){
        //System.out.println(menu.getRights());
        return menuMapper.getMenu_2(menu);
    }
}

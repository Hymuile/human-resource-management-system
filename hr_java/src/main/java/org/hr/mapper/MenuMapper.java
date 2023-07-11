package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.Menu;

import java.util.List;
@Mapper
public interface MenuMapper {
    // 查询所有一级菜单
    public List<Menu> getMenu_1();
    // 通过一级菜单id查询二级菜单
    public List<Menu> getMenu_2(Menu menu);
}

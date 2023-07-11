package org.hr.controller;

import org.hr.model.OrgUnit;
import org.hr.model.User;
import org.hr.model.UserInfo;
import org.hr.modelOv.UnitOV;
import org.hr.service.OrgUnitService;
import org.hr.service.UserInfoService;
import org.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UnitController {
    @Autowired
    OrgUnitService orgUnitService;
    @Autowired
    UserService userService;
    //新建机构
    @PostMapping("/admin/create_unit")
    public Object addOrgUnit(@RequestBody OrgUnit orgUnit){
        Map<String,Object> map=new HashMap<>();
        //先查询机构是否已存在
        OrgUnit unit=orgUnitService.getUnitByName(orgUnit.getName());
        if(unit!=null){
            //机构已存在
            map.put("state",202);
            map.put("msg","机构已存在");
        }else{
            //机构未存在，新建该机构
            //先判断主管用户名是否存在
            User user=userService.findUserByUsername(orgUnit.getManager());
            if(user!=null) {
                int result = orgUnitService.addOrgUnit(orgUnit);
                if (result == 1) {
                    map.put("state", 200);
                    map.put("msg", "新建机构成功");
                } else {
                    map.put("state", 500);
                    map.put("msg", "新建机构失败");
                }
            }else{
                map.put("state", 202);
                map.put("msg", "主管用户名不存在");
            }
        }

        return map;
    }

    //修改机构
    @PutMapping("/admin/modify_unit")
    public Object modifyOrgUnit(@RequestBody OrgUnit orgUnit){
        Map<String,Object> map=new HashMap<>();
        //先判断主管用户名是否存在
        User user=userService.findUserByUsername(orgUnit.getManager());
        if(user!=null) {
            Integer result = orgUnitService.modifyOrgUnit(orgUnit);
            if (result == 1) {
                map.put("state", 200);
                map.put("msg", "修改机构成功");
            } else {
                map.put("state", 500);
                map.put("msg", "修改机构失败");
            }
        }else{
            map.put("state", 202);
            map.put("msg", "主管用户名不存在");
        }

        return map;
    }

    //删除机构
    @DeleteMapping("/admin/deleteunit")
    public Object deleteUnit(String id){
        Map<String,Object> map=new HashMap<>();
        int result=orgUnitService.deleteOrgUnit(id);
        if(result==1){
            map.put("state",200);
            map.put("msg","删除机构成功");
        }else{
            map.put("state",202);
            map.put("msg","删除机构失败");
        }

        return map;
    }

    //通过机构名称查询机构信息
    @GetMapping("/user/getunitbyname")
    public Object getUnitByName(String name,Integer current_index,Integer page_size){
        Map<String,Object> map = new HashMap<>();
        UnitOV unitOV=new UnitOV();
        unitOV.setName(name);
        unitOV.setCurrent_index(current_index);
        unitOV.setPage_size(page_size);
        List<OrgUnit> units=orgUnitService.getUnitsByName(unitOV);
        map.put("state",200);
        map.put("msg","查询机构成功");
        map.put("data",units);
        Integer result_num=orgUnitService.getUnitsNumByName(unitOV);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("allnum",result_num);

        return map;
    }
}

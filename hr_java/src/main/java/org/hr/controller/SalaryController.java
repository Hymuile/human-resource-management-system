package org.hr.controller;

import org.hr.model.OrgPost;
import org.hr.model.Salary;
import org.hr.modelOv.SalaryOV;
import org.hr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    //新增薪酬
    @PostMapping("/admin/create_salary")
    public Object createSalary(@RequestBody Salary salary){
        Map<String,Object> map=new HashMap<>();
        int result=salaryService.addSalary(salary);
        if(result==1){
            map.put("state",200);
            map.put("msg","添加薪酬成功");
        }else{
            map.put("state",202);
            map.put("msg","添加薪酬失败");
        }

        return map;
    }

    //修改薪资
    @PutMapping("/admin/modify_salary")
    public Object modifyUnit(@RequestBody Salary salary){
        Map<String,Object> map=new HashMap<>();
        int result = salaryService.modifySalary(salary);
        if(result==1){
            map.put("state",200);
            map.put("msg","修改岗位成功");
        }else{
            map.put("state",202);
            map.put("msg","修改岗失败");
        }

        return map;
    }

    //通过薪酬名称查询薪酬（模糊查询）
    @GetMapping("/admin/get_all_salary")
    public Object getSalarysByName(String notes,Integer current_index,Integer page_size){
        Map<String,Object> map = new HashMap<>();
        SalaryOV salaryOV=new SalaryOV();
        salaryOV.setNotes(notes);
        salaryOV.setCurrent_index(current_index);
        salaryOV.setPage_size(page_size);
        List<Salary> salaries=salaryService.findSalarysByNotes(salaryOV);
        Integer total=salaryService.findSalaryNumByNotes(salaryOV);

        map.put("state",200);
        map.put("msg","通过名称查询薪酬成功");
        map.put("data",salaries);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("total",total);

        return map;
    }

    //按名称查询薪酬
    @GetMapping("/user/get_salary")
    public Object getSalaryByNotes(String notes){
        Map<String,Object> map=new HashMap<>();
        Salary salary=salaryService.findSalaryByNotes(notes);
        map.put("state",200);
        map.put("msg","查询薪酬成功");
        map.put("data",salary);

        return map;
    }

    //按id删除薪酬级别
    @DeleteMapping("/admin/delete_salary")
    public Object deleteSalary(String id){
        Map<String,Object> map=new HashMap<>();
        Integer result = salaryService.deleteSalaryById(id);
        if(result ==1){
            map.put("state",200);
            map.put("msg","删除薪酬级别成功");
        }else{
            map.put("state",202);
            map.put("msg","删除薪酬级别失败");
        }

        return map;
    }
}

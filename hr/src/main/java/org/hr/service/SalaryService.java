package org.hr.service;


import org.hr.mapper.SalaryMapper;
import org.hr.model.Salary;
import org.hr.modelOv.SalaryOV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    @Autowired
    SalaryMapper salaryMapper;

    //新增薪酬
    public int addSalary(Salary salary){
        return salaryMapper.addSalary(salary);
    }

    //通过薪酬名查询薪酬（模糊查询）
    public List<Salary> findSalarysByNotes(SalaryOV salaryOV){
        return salaryMapper.findSalarysByNotes(salaryOV);
    }

    //通过薪酬名查询薪酬数量（模糊查询）
    public Integer findSalaryNumByNotes(SalaryOV salaryOV){
        return salaryMapper.findSalaryNumByNotes(salaryOV);
    }

    //修改薪酬
    public int modifySalary(Salary salary){
        return salaryMapper.modifySalary(salary);
    }

    //按名称查询薪酬
    public Salary findSalaryByNotes(String notes){
        return salaryMapper.findSalaryByNotes(notes);
    }

    //删除薪酬
    public int deleteSalaryById(String id){
        return salaryMapper.deleteSalaryById(id);
    }
}

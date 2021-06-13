package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.Salary;
import org.hr.modelOv.SalaryOV;

import java.util.List;

@Mapper
public interface SalaryMapper {
    //新增薪酬
    public int addSalary(Salary salary);
    //通过薪酬名查询薪酬（模糊查询）
    public List<Salary> findSalarysByNotes(SalaryOV salaryOV);
    //通过薪酬名查询薪酬数量（模糊查询）
    public int findSalaryNumByNotes(SalaryOV salaryOV);
    //修改薪酬级别
    public int modifySalary(Salary salary);
    //通过薪酬名查询薪酬（精确查询）
    public Salary findSalaryByNotes(String notes);
    //删除薪酬级别
    public int deleteSalaryById(String id);

}

package org.hr.service;

import org.hr.mapper.HolidayMapper;
import org.hr.model.HolidayDetail;
import org.hr.model.HolidayFlow;
import org.hr.modelOv.UserHoliday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {
    @Autowired
    HolidayMapper holidayMapper;

    //查询个人请假（精确查询）
    public List<HolidayDetail> getUserHoliday(String user){
        return holidayMapper.getUserHoliday(user);
    }
    //个人假期明细
    /*public List<HolidayDetail> getUserHolidayDetail(String user){
        return holidayMapper.getUserHolidayDetail(user);
    }*/

    //查询所有人请假（模糊查询）
    public List<HolidayDetail> getUserHolidays(UserHoliday userHoliday){
        return holidayMapper.getUserHolidays(userHoliday);
    }

    //查询所有人请假数量（模糊查询）
    public Integer getUserHolidayNum(UserHoliday userHoliday){
        return holidayMapper.getUserHolidayNum(userHoliday);
    }

    //申请新增请假
    public Integer addHolidayFlow(HolidayFlow holidayFlow){
        return holidayMapper.addHolidayFlow(holidayFlow);
    }

    //申请修改请假
    public Integer modifyHolidayFlow(HolidayFlow holidayFlow){
        return holidayMapper.modifyHolidayFlow(holidayFlow);
    }

    //修改请假
    public Integer modifyHoliday(HolidayDetail holidayDetail){
        return holidayMapper.modifyHoliday(holidayDetail);
    }

    //查询本人发起的所有请假流程
    public List<HolidayFlow> getUserHolidayFlow(UserHoliday userHoliday){
        return holidayMapper.getUserHolidayFlow(userHoliday);
    }

    //查询本人发起的所有请假流程数量
    public Integer getUserHolidayFlowNum(UserHoliday userHoliday){
        return holidayMapper.getUserHolidayFlowNum(userHoliday);
    }

    //查询所有待审批的请假流程
    public List<HolidayFlow> getProvingHolidayFlow(UserHoliday userHoliday){
        return holidayMapper.getProvingHolidayFlow(userHoliday);
    }

    //查询所有待审批的请假流程数量
    public Integer getProvingHolidayFlowNum(UserHoliday userHoliday){
        return holidayMapper.getProvingHolidayFlowNum(userHoliday);
    }

    //通过id查询请假流程
    public HolidayFlow getHolidayFlowById(String id){
        return holidayMapper.getHolidayFlowById(id);
    }

    //新增请假
    public Integer addHoliday(HolidayDetail holidayDetail){
        return holidayMapper.addHoliday(holidayDetail);
    }

    //更新流程状态
    public Integer updateHolidayFlowState(HolidayFlow holidayFlow){
        return holidayMapper.updateHolidayFlowState(holidayFlow);
    }
}

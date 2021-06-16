package org.hr.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.hr.model.HolidayDetail;
import org.hr.model.HolidayFlow;
import org.hr.modelOv.UserHoliday;

import java.util.List;

@Mapper
public interface HolidayMapper {
    //查询个人请假
    public List<HolidayDetail> getUserHoliday(String user);
    //个人请假明细
    //public List<HolidayDetail> getUserHolidayDetail(String user);
    //申请假期
    public int addHolidayFlow(HolidayFlow holidayFlow);
    //新增假期
    public int addHoliday(HolidayDetail holidayDetail);
    //申请修改假期
    public int modifyHolidayFlow(HolidayFlow holidayFlow);
    //查询所有人请假（模糊查询）
    public List<HolidayDetail> getUserHolidays(UserHoliday userHoliday);
    //查询所有人请假数（模糊查询）
    public Integer getUserHolidayNum(UserHoliday userHoliday);
    //修改人员请假信息
    public int modifyHoliday(HolidayDetail holidayDetail);
    //查询本人发起的所有请假流程
    public List<HolidayFlow> getUserHolidayFlow(UserHoliday userHoliday);
    //查询本人发起的所有请假流程数量
    public Integer getUserHolidayFlowNum(UserHoliday userHoliday);
    //查询所有待审批的请假流程
    public List<HolidayFlow> getProvingHolidayFlow(UserHoliday userHoliday);
    //查询所有待审批的请假流程数量
    public Integer getProvingHolidayFlowNum(UserHoliday userHoliday);
    //通过id查询请假流程
    public HolidayFlow getHolidayFlowById(String id);
    //更新流程状态和信息
    public Integer updateHolidayFlowState(HolidayFlow holidayFlow);
}

package org.hr.controller;

import org.hr.model.*;
import org.hr.modelOv.UserHoliday;
import org.hr.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HolidayController {
    @Autowired
    HolidayService holidayService;

    //通过用户查询假期
    @GetMapping("/user/get_user_holiday")
    public Object getUserHoliday(){
        Map<String,Object> map=new HashMap<>();
        //获取登录的用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        List<HolidayDetail> holidays=holidayService.getUserHoliday(username);
        map.put("state",200);
        map.put("msg","查询本人请假成功");
        map.put("data",holidays);

        return map;
    }

    /*@GetMapping("/user/get_user_holiday_detail")
    public Object getUserHolidayDetail(){
        Map<String,Object> map=new HashMap<>();
        //获取登录的用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        List<HolidayDetail> holidays=holidayService.getUserHolidayDetail(username);
        map.put("state",200);
        map.put("msg","查询本人请假成功");
        map.put("data",holidays);

        return map;
    }*/

    //查询所有人请假（模糊查询）
    @GetMapping("/admin/get_all_holiday")
    public Object getAllholiday(String username,Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        UserHoliday userHoliday=new UserHoliday();
        userHoliday.setUsername(username);
        userHoliday.setCurrent_index(current_index);
        userHoliday.setPage_size(page_size);
        List<HolidayDetail> holidays=holidayService.getUserHolidays(userHoliday);
        Integer total=holidayService.getUserHolidayNum(userHoliday);
        map.put("state",200);
        map.put("smg","查询所有人请假成功");
        map.put("data",holidays);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("total",total);

        return map;
    }

    //申请新增请假
    @PostMapping("/user/add_holiday")
    public Object addHolidayFlow(@RequestBody HolidayFlow holidayFlow) throws  Exception{
        Map<String,Object> map=new HashMap<>();
        //获取用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        holidayFlow.setUser_name(user.getUsername());
        DateFormat df= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        holidayFlow.setType(0);
        holidayFlow.setApply_date(df.parse(df.format(new Date())));
        holidayFlow.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));


        holidayFlow.setState(0);
        Integer result = holidayService.addHolidayFlow(holidayFlow);
        if(result==1){
            map.put("state",200);
            map.put("msg","申请新增请假成功");
        }else{
            map.put("state",202);
            map.put("msg","申请新增请假失败");
        }

        return map;
    }

    //申请修改请假
    @PostMapping("/user/modify_user_holiday")
    public Object modifyHolidayFlow(@RequestBody HolidayFlow holidayFlow) throws  Exception{
        Map<String,Object> map=new HashMap<>();
        //获取用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        holidayFlow.setUser_name(user.getUsername());
        DateFormat df= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        holidayFlow.setType(1);
        holidayFlow.setApply_date(df.parse(df.format(new Date())));
        holidayFlow.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));


        holidayFlow.setState(0);
        Integer result = holidayService.modifyHolidayFlow(holidayFlow);
        if(result==1){
            map.put("state",200);
            map.put("msg","申请修改请假成功");
        }else{
            map.put("state",202);
            map.put("msg","申请修改请假失败");
        }

        return map;
    }

    //修改请假
    @PostMapping("/admin/modify_holiday")
    public Object modifyHoliday(@RequestBody HolidayDetail holidayDetail){
        Map<String,Object> map=new HashMap<>();
        Integer result=holidayService.modifyHoliday(holidayDetail);
        if(result ==1){
            map.put("state",200);
            map.put("msg","修改用户请假成功");
        }else{
            map.put("state",202);
            map.put("msg","修改用户请假失败");
        }

        return map;
    }

    //查询所有本人发起的请假流程
    @GetMapping("/user/get_user_holidayflow")
    public Object getUserHolidayFlow(Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserHoliday userHoliday=new UserHoliday();
        userHoliday.setUsername(user.getUsername());
        //userHoliday.setUsername(user_name);
        userHoliday.setCurrent_index(current_index);
        userHoliday.setPage_size(page_size);

        List<HolidayFlow> holidayFlows =holidayService.getUserHolidayFlow(userHoliday);
        Integer total=holidayService.getUserHolidayFlowNum(userHoliday);
        map.put("state",200);
        map.put("msg","获取本人发起的流程请假成功");
        map.put("data", holidayFlows);
        map.put("total",total);

        return map;
    }

    //查询所有待审批的请假流程
    @GetMapping("/admin/get_proving_holidayflow")
    public Object getProvingHolidayFlow(String username,Date bdate,Date edate,Integer current_index,Integer page_size){
        System.out.println("========================");
        Map<String,Object> map=new HashMap<>();
        UserHoliday userHoliday=new UserHoliday();
        userHoliday.setUsername(username);
        userHoliday.setBdate(bdate);
        userHoliday.setEDate(edate);
        //int date_num = (int) ((userHoliday.getEDate().getTime()-userHoliday.getBdate().getTime()) / (1000 * 60 * 60 * 24));
        //userHoliday.setDate_num(date_num);

        //System.out.println("num:"+date_num);
        userHoliday.setPage_size(page_size);
        userHoliday.setCurrent_index(current_index);

        List<HolidayFlow> holidayFlows =holidayService.getProvingHolidayFlow(userHoliday);
        Integer total=holidayService.getProvingHolidayFlowNum(userHoliday);
        map.put("state",200);
        map.put("msg","获取待审批的流程成功");
        map.put("data", holidayFlows);
        map.put("total",total);

        return map;
    }

    //处理待审批的请假流程
    @PutMapping("/admin/deal_holiday_flow")
    public Object dealHolidayFlow(String id,Integer type) throws Exception{
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DateFormat df= new SimpleDateFormat("yyy/MM/dd HH:mm:ss");

        //将流程数据取出来
        HolidayFlow holidayFlow =holidayService.getHolidayFlowById(id);
        if(type==0){//0新建，1修改
            // 审批同意
            if(holidayFlow.getType()==0){

                //新建请假
                HolidayDetail holidayDetail =new HolidayDetail();
                holidayDetail.setUser(holidayFlow.getUser_name());
                holidayDetail.setBdate(holidayFlow.getBdate());
                holidayDetail.setEdate(holidayFlow.getEdate());
                holidayDetail.setApply_date(holidayFlow.getApply_date());
                holidayDetail.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));


                int result_1=holidayService.addHoliday(holidayDetail);

                //更新流程状态和审批信息
                HolidayFlow holidayFlow2 =new HolidayFlow();


                holidayFlow2.setId(holidayFlow.getId());
                holidayFlow2.setApprover_id(user.getUsername());
                holidayFlow2.setApply_date(df.parse(df.format(new Date())));
                holidayFlow2.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));
                holidayFlow2.setApprove_result(0);
                holidayFlow2.setState(1);

                int result_2=holidayService.updateHolidayFlowState(holidayFlow2);


                if(result_1==1&&result_2==1){
                    map.put("state",200);
                    map.put("msg","处理流程成功");
                }else{
                    map.put("state",202);
                    map.put("msg","处理流程失败");
                }
            }else{

                //修改请假
                HolidayDetail holidayDetail =new HolidayDetail();
                holidayDetail.setId(holidayFlow.getPre_id());
                holidayDetail.setUser(holidayFlow.getUser_name());
                holidayDetail.setBdate(holidayFlow.getBdate());
                holidayDetail.setEdate(holidayFlow.getEdate());
                holidayDetail.setApply_date(holidayFlow.getApply_date());
                holidayDetail.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));

                int result_1=holidayService.modifyHoliday(holidayDetail);
                //更新流程状态和审批信息
                HolidayFlow holidayFlow2 =new HolidayFlow();
                holidayFlow2.setId(holidayFlow.getId());
                holidayFlow2.setApprover_id(user.getUsername());
                holidayFlow2.setApply_date(df.parse(df.format(new Date())));
                holidayFlow2.setBdate(holidayFlow.getBdate());
                holidayFlow2.setEdate(holidayFlow.getEdate());
                holidayFlow2.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));
                holidayFlow2.setApprove_result(0);
                holidayFlow2.setState(1);

                int result_2=holidayService.updateHolidayFlowState(holidayFlow2);
                if(result_1==1&&result_2==1){
                    map.put("state",200);
                    map.put("msg","处理流程成功");
                }else{
                    map.put("state",202);
                    map.put("msg","处理流程失败");
                }
            }
        }else{

            // 审批不同意
            //更新流程状态和审批信息
            HolidayFlow holidayFlow2 =new HolidayFlow();
            holidayFlow2.setId(holidayFlow.getId());
            holidayFlow2.setApprover_id(user.getUsername());
            holidayFlow2.setApply_date(df.parse(df.format(new Date())));
            holidayFlow2.setBdate(holidayFlow.getBdate());
            holidayFlow2.setEdate(holidayFlow.getEdate());
            holidayFlow2.setDate_num((int) ((holidayFlow.getEdate().getTime()-holidayFlow.getBdate().getTime()) / (1000 * 60 * 60 * 24)));
            holidayFlow2.setApprove_result(1);
            holidayFlow2.setState(1);

            int result_2=holidayService.updateHolidayFlowState(holidayFlow2);
            if(result_2==1){
                map.put("state",200);
                map.put("msg","处理流程成功");
            }else{
                map.put("state",202);
                map.put("msg","处理流程失败");
            }
        }
        return map;
    }
}

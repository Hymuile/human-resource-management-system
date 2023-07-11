package org.hr.controller;

import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import org.hr.model.CertFlow;
import org.hr.model.EmployeeCertificate;
import org.hr.model.User;
import org.hr.modelOv.UserCert;
import org.hr.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.ServletContextPropertyUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CertificateController {
    @Autowired
    CertificateService certificateService;

    //通过用户查询证书
    @GetMapping("/user/get_user_cert")
    public Object getUserCert(){
        Map<String,Object> map=new HashMap<>();
        //获取登录的用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        List<EmployeeCertificate> certificates=certificateService.getUserCert(username);
        map.put("state",200);
        map.put("msg","查询本人证书成功");
        map.put("data",certificates);

        return map;
    }

    //查询所有人证书（模糊查询）
    @GetMapping("/admin/get_all_cert")
    public Object getAllCert(String username,String cert_name,Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        UserCert userCert=new UserCert();
        userCert.setUsername(username);
        userCert.setCert_name(cert_name);
        userCert.setCurrent_index(current_index);
        userCert.setPage_size(page_size);
        List<EmployeeCertificate> certificates=certificateService.getUserCerts(userCert);
        Integer total=certificateService.getUserCertNum(userCert);
        map.put("state",200);
        map.put("smg","查询所有人证书成功");
        map.put("data",certificates);
        map.put("current_index",current_index);
        map.put("page_size",page_size);
        map.put("total",total);

        return map;
    }

    //申请新增证书
    @PostMapping("/user/add_cert")
    public Object addCertFlow(@RequestBody CertFlow certFlow) throws  Exception{
        Map<String,Object> map=new HashMap<>();
        //获取用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        certFlow.setUser_name(user.getUsername());
        DateFormat df= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        certFlow.setType(0);
        certFlow.setApply_date(df.parse(df.format(new Date())));
        System.out.println("======certFlow-date:"+certFlow.getCert_date());
        certFlow.setState(0);
        Integer result = certificateService.addCertFlow(certFlow);
        if(result==1){
            map.put("state",200);
            map.put("msg","申请新增证书成功");
        }else{
            map.put("state",202);
            map.put("msg","申请新增证书失败");
        }

        return map;
    }

    //申请修改证书
    @PostMapping("/user/modify_user_cert")
    public Object modifyCertFlow(@RequestBody CertFlow certFlow) throws  Exception{
        Map<String,Object> map=new HashMap<>();
        //获取用户名
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        certFlow.setUser_name(user.getUsername());
        DateFormat df= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        certFlow.setType(1);
        certFlow.setApply_date(df.parse(df.format(new Date())));
        certFlow.setState(0);
        Integer result = certificateService.modifyCertFlow(certFlow);
        if(result==1){
            map.put("state",200);
            map.put("msg","申请修改证书成功");
        }else{
            map.put("state",202);
            map.put("msg","申请修改证书失败");
        }

        return map;
    }

    //修改证书
    @PostMapping("/admin/modify_cert")
    public Object modifyCert(@RequestBody EmployeeCertificate employeeCertificate){
        Map<String,Object> map=new HashMap<>();
        Integer result=certificateService.modifyCert(employeeCertificate);
        if(result ==1){
            map.put("state",200);
            map.put("msg","修改用户证书成功");
        }else{
            map.put("state",202);
            map.put("msg","修改用户证书失败");
        }

        return map;
    }

    //查询所有本人发起的证书流程
    @GetMapping("/user/get_user_certflow")
    public Object getUserCertFlow(String cert_name,Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserCert userCert=new UserCert();
        userCert.setUsername(user.getUsername());
        userCert.setCert_name(cert_name);
        userCert.setCurrent_index(current_index);
        userCert.setPage_size(page_size);

        List<CertFlow> certFlows=certificateService.getUserCertFlow(userCert);
        Integer total=certificateService.getUserCertFlowNum(userCert);
        map.put("state",200);
        map.put("msg","获取本人发起的流程证书成功");
        map.put("data",certFlows);
        map.put("total",total);

        return map;
    }

    //查询所有待审批的证书流程
    @GetMapping("/admin/get_proving_certflow")
    public Object getProvingCertFlow(String username,String cert_name,Integer current_index,Integer page_size){
        Map<String,Object> map=new HashMap<>();
        UserCert userCert=new UserCert();
        userCert.setUsername(username);
        userCert.setPage_size(page_size);
        userCert.setCurrent_index(current_index);
        userCert.setCert_name(cert_name);
        System.out.println("cert_name:"+cert_name);
        List<CertFlow> certFlows=certificateService.getProvingCertFlow(userCert);
        Integer total=certificateService.getProvingCertFlowNum(userCert);
        map.put("state",200);
        map.put("msg","获取待审批的流程成功");
        map.put("data",certFlows);
        map.put("total",total);

        return map;
    }

    //处理待审批的证书流程
    @PutMapping("/admin/deal_cert_flow")
    public Object dealCertFlow(String id,Integer type) throws Exception{
        Map<String,Object> map=new HashMap<>();
        User user=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DateFormat df= new SimpleDateFormat("yyy/MM/dd HH:mm:ss");

        //将流程数据取出来
        CertFlow certFlow=certificateService.getCertFlowById(id);
        if(type==0){
            // 审批同意
            if(certFlow.getType()==0){
                //新增证书
                //先向员工证书表插入数据
                EmployeeCertificate employeeCertificate=new EmployeeCertificate();
                employeeCertificate.setUser(certFlow.getUser_name());
                employeeCertificate.setCert_no(certFlow.getCert_no());
                employeeCertificate.setCert_name(certFlow.getCert_name());
                employeeCertificate.setCert_date(certFlow.getCert_date());

                int result_1=certificateService.addCert(employeeCertificate);

                //更新流程状态和审批信息
                CertFlow certFlow_2=new CertFlow();
                certFlow_2.setId(certFlow.getId());
                certFlow_2.setApprover_id(user.getUsername());
                certFlow_2.setApply_date(df.parse(df.format(new Date())));
                certFlow_2.setApprove_result(0);
                certFlow_2.setState(1);

                int result_2=certificateService.updateCertFlowState(certFlow_2);
                if(result_1==1&&result_2==1){
                    map.put("state",200);
                    map.put("msg","处理流程成功");
                }else{
                    map.put("state",202);
                    map.put("msg","处理流程失败");
                }
            }else{
                //修改证书
                //先更新员工证书表
                EmployeeCertificate employeeCertificate=new EmployeeCertificate();
                employeeCertificate.setId(certFlow.getPre_id());
                employeeCertificate.setUser(certFlow.getUser_name());
                employeeCertificate.setCert_no(certFlow.getCert_no());
                employeeCertificate.setCert_name(certFlow.getCert_name());
                employeeCertificate.setCert_date(certFlow.getCert_date());

                int result_1=certificateService.modifyCert(employeeCertificate);
                //更新流程状态和审批信息
                CertFlow certFlow_2=new CertFlow();
                certFlow_2.setId(certFlow.getId());
                certFlow_2.setApprover_id(user.getUsername());
                certFlow_2.setApply_date(df.parse(df.format(new Date())));
                certFlow_2.setApprove_result(0);
                certFlow_2.setState(1);

                int result_2=certificateService.updateCertFlowState(certFlow_2);
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
            CertFlow certFlow_2=new CertFlow();
            certFlow_2.setId(certFlow.getId());
            certFlow_2.setApprover_id(user.getUsername());
            certFlow_2.setApply_date(df.parse(df.format(new Date())));
            certFlow_2.setApprove_result(1);
            certFlow_2.setState(1);

            int result_2=certificateService.updateCertFlowState(certFlow_2);
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

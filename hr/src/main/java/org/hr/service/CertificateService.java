package org.hr.service;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import org.hr.mapper.CertificateMapper;
import org.hr.model.CertFlow;
import org.hr.model.EmployeeCertificate;
import org.hr.model.User;
import org.hr.modelOv.UserCert;
import org.hr.modelOv.UserSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class CertificateService {
    @Autowired
    CertificateMapper certificateMapper;

    //查询个人证书（精确查询）
    public List<EmployeeCertificate> getUserCert(String user){
        return certificateMapper.getUserCert(user);
    }

    //查询所有人证书（模糊查询）
    public List<EmployeeCertificate> getUserCerts(UserCert userCert){
        return certificateMapper.getUserCerts(userCert);
    }

    //查询所有人证书数量（模糊查询）
    public Integer getUserCertNum(UserCert userCert){
        return certificateMapper.getUserCertNum(userCert);
    }

    //申请新增证书
    public Integer addCertFlow(CertFlow certFlow){
        return certificateMapper.addCertFlow(certFlow);
    }

    //申请修改证书
    public Integer modifyCertFlow(CertFlow certFlow){
        return certificateMapper.modifyCertFlow(certFlow);
    }

    //修改证书
    public Integer modifyCert(EmployeeCertificate employeeCertificate){
        return certificateMapper.modifyCert(employeeCertificate);
    }

    //查询本人发起的所有证书流程
    public List<CertFlow> getUserCertFlow(UserCert userCert){
        return certificateMapper.getUserCertFlow(userCert);
    }

    //查询本人发起的所有证书流程数量
    public Integer getUserCertFlowNum(UserCert userCert){
        return certificateMapper.getUserCertFlowNum(userCert);
    }

    //查询所有待审批的证书流程
    public List<CertFlow> getProvingCertFlow(UserCert userCert){
        return certificateMapper.getProvingCertFlow(userCert);
    }

    //查询所有待审批的证书流程数量
    public Integer getProvingCertFlowNum(UserCert userCert){
        return certificateMapper.getProvingCertFlowNum(userCert);
    }

    //通过id查询证书流程
    public CertFlow getCertFlowById(String id){
        return certificateMapper.getCertFlowById(id);
    }

    //新增证书
    public Integer addCert(EmployeeCertificate employeeCertificate){
        return certificateMapper.addCert(employeeCertificate);
    }

    //更新流程状态
    public Integer updateCertFlowState(CertFlow certFlow){
        return certificateMapper.updateCertFlowState(certFlow);
    }
}

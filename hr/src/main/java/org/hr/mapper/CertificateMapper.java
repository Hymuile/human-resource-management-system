package org.hr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.hr.model.CertFlow;
import org.hr.model.EmployeeCertificate;
import org.hr.modelOv.UserCert;

import java.util.List;

@Mapper
public interface CertificateMapper {
    //查询个人证书
    public List<EmployeeCertificate> getUserCert(String user);
    //申请新增证书
    public int addCertFlow(CertFlow certFlow);
    //新增证书
    public int addCert(EmployeeCertificate employeeCertificate);
    //申请修改证书
    public int modifyCertFlow(CertFlow certFlow);
    //查询所有人证书（模糊查询）
    public List<EmployeeCertificate> getUserCerts(UserCert userCert);
    //查询所有人证书数量（模糊查询）
    public Integer getUserCertNum(UserCert userCert);
    //修改人员证书
    public int modifyCert(EmployeeCertificate employeeCertificate);
    //查询本人发起的所有证书流程
    public List<CertFlow> getUserCertFlow(UserCert userCert);
    //查询本人发起的所有证书流程数量
    public Integer getUserCertFlowNum(UserCert userCert);
    //查询所有待审批的证书流程
    public List<CertFlow> getProvingCertFlow(UserCert userCert);
    //查询所有待审批的证书流程数量
    public Integer getProvingCertFlowNum(UserCert userCert);
    //通过id查询证书流程
    public CertFlow getCertFlowById(String id);
    //更新流程状态和信息
    public Integer updateCertFlowState(CertFlow certFlow);
}

package org.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CertFlow {
    private String id;
    private Integer type;
    private String pre_id;
    private String user_name;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date apply_date;
    private String cert_no;
    private String cert_name;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date cert_date;
    private String approver_id;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date approve_date;
    private Integer approve_result;
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public String getCert_no() {
        return cert_no;
    }

    public void setCert_no(String cert_no) {
        this.cert_no = cert_no;
    }

    public String getCert_name() {
        return cert_name;
    }

    public void setCert_name(String cert_name) {
        this.cert_name = cert_name;
    }

    public Date getCert_date() {
        return cert_date;
    }

    public void setCert_date(Date cert_date) {
        this.cert_date = cert_date;
    }

    public String getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(String approver_id) {
        this.approver_id = approver_id;
    }

    public Date getApprove_date() {
        return approve_date;
    }

    public void setApprove_date(Date approve_date) {
        this.approve_date = approve_date;
    }

    public Integer getApprove_result() {
        return approve_result;
    }

    public void setApprove_result(Integer approve_result) {
        this.approve_result = approve_result;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

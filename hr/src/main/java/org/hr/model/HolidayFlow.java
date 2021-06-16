package org.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HolidayFlow {
    private String id;
    private String user_name;
    private String pre_id;
    private Integer type;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date apply_date;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date bdate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date edate;
    private Integer date_num;

    @Override
    public String toString() {
        return "HolidayFlow{" +
                "id='" + id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", type=" + type +
                ", apply_date=" + apply_date +
                ", bdate=" + bdate +
                ", edate=" + edate +
                ", date_num=" + date_num +
                ", approver_id='" + approver_id + '\'' +
                ", approve_date=" + approve_date +
                ", approve_result=" + approve_result +
                ", state=" + state +
                '}';
    }

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

    public String getUser_name(){return user_name;}

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public Integer getDate_num() {
        return date_num;
    }

    public void setDate_num(Integer date_num) {
        this.date_num = date_num;
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

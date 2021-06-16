package org.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class HolidayDetail {
    private String id;
    private String user;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date bdate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date edate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date apply_date;
    private Integer date_num;
    private String notes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public Integer getDate_num() {
        return date_num;
    }

    public void setDate_num(Integer date_num) {
        this.date_num = date_num;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

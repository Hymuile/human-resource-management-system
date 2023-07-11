package org.hr.modelOv;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserHoliday {
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date bdate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date edate;
    //private Integer date_num;
    private Integer current_index;
    private Integer page_size;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getEDate() {
        return edate;
    }

    public void setEDate(Date edate) {
        this.edate = edate;
    }

   /* public Integer getDate_num() {
        return date_num;
    }

    public void setDate_num(Integer date_num) {
        this.date_num = date_num;
    }*/

    public Integer getCurrent_index() {
        return current_index;
    }

    public void setCurrent_index(Integer current_index) {
        this.current_index = current_index;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }
}

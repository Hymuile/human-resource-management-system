package org.hr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EmployeeCertificate {
    private String id;
    private String user;
    private String cert_no;
    private String cert_name;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date cert_date;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

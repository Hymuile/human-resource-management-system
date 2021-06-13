package org.hr.model;

public class Holiday {
    private String id;
    private String user;
    private Integer total_num;
    private Integer used_num;
    private Integer applicating_num;
    private Integer left_num;

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

    public Integer getTotal_num() {
        return total_num;
    }

    public void setTotal_num(Integer total_num) {
        this.total_num = total_num;
    }

    public Integer getUsed_num() {
        return used_num;
    }

    public void setUsed_num(Integer used_num) {
        this.used_num = used_num;
    }

    public Integer getApplicating_num() {
        return applicating_num;
    }

    public void setApplicating_num(Integer applicating_num) {
        this.applicating_num = applicating_num;
    }

    public Integer getLeft_num() {
        return left_num;
    }

    public void setLeft_num(Integer left_num) {
        this.left_num = left_num;
    }
}

package org.hr.modelOv;

public class UserCert {
    private String username;
    private String cert_name;
    private Integer current_index;
    private Integer page_size;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCert_name() {
        return cert_name;
    }

    public void setCert_name(String cert_name) {
        this.cert_name = cert_name;
    }

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

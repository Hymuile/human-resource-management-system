package org.hr.modelOv;

public class PostOV {
    private String name;
    private int current_index;
    private int page_size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrent_index() {
        return current_index;
    }

    public void setCurrent_index(int current_index) {
        this.current_index = current_index;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
}

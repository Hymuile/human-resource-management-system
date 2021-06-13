package org.hr.modelOv;

public class SalaryOV {
    private String id;
    private String notes;
    private Integer current_index;
    private Integer page_size;

    @Override
    public String toString() {
        return "SalaryOV{" +
                "id='" + id + '\'' +
                ", notes='" + notes + '\'' +
                ", current_index=" + current_index +
                ", page_size=" + page_size +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

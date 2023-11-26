package com.leminhtien.dto;

import java.util.Date;

public class BaseDTO {
    public Date getCreateDate() {
        return createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String createBy;
    private String modifyBy;

    public Long getId() {
        return id;
    }
}

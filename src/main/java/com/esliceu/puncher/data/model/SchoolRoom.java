package com.esliceu.puncher.data.model;

import javax.persistence.*;

@Entity
public class SchoolRoom {

    @Id
    private Integer code;

    private String description;

    public SchoolRoom(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

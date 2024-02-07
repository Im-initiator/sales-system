package com.leminhtien.dto;

import java.util.Objects;

public class TransportDTO extends BaseDTO{

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(obj==null||obj.getClass()!=this.getClass()){
            return false;
        }
        TransportDTO transportDTO = (TransportDTO) obj;
        return this.getId().equals(transportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}

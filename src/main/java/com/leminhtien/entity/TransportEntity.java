package com.leminhtien.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "transport")
@Entity
public class TransportEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private String code;

    @OneToMany(mappedBy = "transport",fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}

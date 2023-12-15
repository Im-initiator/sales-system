package com.leminhtien.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_genter")
public class CategoryGenderEntity extends BaseEntity{
    @Column
    private String name;

    @Column
    private String code;

    @OneToMany(mappedBy = "categoryGender",cascade = CascadeType.ALL)
    private List<ProductEntity> products;

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

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
}

package com.leminhtien.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_gender")
public class CategoryGenderEntity extends BaseEntity{
    @Column
    private String name;
    @Column
    private String code;

    @OneToMany(mappedBy = "categoryGender",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProductEntity> products;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "gender_shop",joinColumns = @JoinColumn(name = "category_id"),inverseJoinColumns = @JoinColumn(name = "gender_id"))
    private List<ShopEntity> shops;

    public List<ShopEntity> getShops() {
        return shops;
    }

    public void setShops(List<ShopEntity> shops) {
        this.shops = shops;
    }

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

package com.leminhtien.dto;

import com.leminhtien.entity.ProductEntity;

import java.util.List;

public class CategoryGenderDTO extends BaseDTO{
    private String name;

    private String code;

    private List<ProductDTO> products;

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

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}

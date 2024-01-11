package com.leminhtien.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="category")
public class CategoryEntity extends BaseEntity{
	@Column
	private String name;
	
	@Column
	private String code;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<ProductEntity> products;

	@ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<ShopEntity> shops;

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

	public List<ShopEntity> getShops() {
		return shops;
	}

	public void setShops(List<ShopEntity> shops) {
		this.shops = shops;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null){
			return false;
		}
		if (obj==this){
			return true;
		}
		if (obj.getClass()!=this.getClass()){
			return false;
		}

		CategoryEntity category = (CategoryEntity) obj;
		return category.getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}
}

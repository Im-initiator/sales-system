package com.leminhtien.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="size")
public class SizeEntity extends BaseEntity{
	@Column
	private String name;

	@ManyToMany(mappedBy = "sizes")
	private List<ProductEntity> products;

	@ManyToMany(mappedBy = "sizes")
	private List<ShopEntity> shops;



	public List<ProductEntity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ShopEntity> getShops() {
		return shops;
	}

	public void setShops(List<ShopEntity> shops) {
		this.shops = shops;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null){
			return false;
		}

		if (obj.getClass()!=this.getClass()){
			return false;
		}

		if (this==obj){
			return true;
		}

		SizeEntity size = (SizeEntity) obj;
		return this.getId().equals(size.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getId());
	}
}


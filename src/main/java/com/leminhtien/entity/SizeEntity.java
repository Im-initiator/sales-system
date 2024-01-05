package com.leminhtien.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="size")
public class SizeEntity extends BaseEntity{
	@Column
	private String name;

	@ManyToMany(mappedBy = "sizes",cascade = CascadeType.ALL)
	private List<ProductEntity> products;
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
}

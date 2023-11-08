package com.leminhtien.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="size")
public class SizeEntity extends BaseEntity{
	@Column
	private String name;
	
	@ManyToMany(mappedBy = "sizes",cascade = CascadeType.ALL)
	private List<ProductEntity> products;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package com.leminhtien.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductEntity extends BaseEntity{
	@Column
	private String name;
	@Column
	private Float prize;
	@Column(name="short_description")
	private String shortDescription;
	@Column
	private Integer quantity;
	public List<SizeEntity> getSizes() {
		return sizes;
	}
	public void setSizes(List<SizeEntity> sizes) {
		this.sizes = sizes;
	}
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	@Column
	private String content;
	@Column(name="sell_number")
	private Integer sellNumber;
	@Column
	private String img;
	
	@ManyToMany
	@JoinTable(name="product_size",joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name="size_id"))
	private List<SizeEntity> sizes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getPrize() {
		return prize;
	}
	public void setPrize(Float prize) {
		this.prize = prize;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSellNumber() {
		return sellNumber;
	}
	public void setSellNumber(Integer sellNumber) {
		this.sellNumber = sellNumber;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}


}

package com.leminhtien.entity;

import com.mysql.cj.protocol.ColumnDefinition;

import java.util.List;

import javax.persistence.*;

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
	@Column(columnDefinition = "TEXT")
	private String content;
	@Column(name="sell_number")
	private Integer sellNumber;
	@Column
	private String img;
	
	@ManyToMany
	@JoinTable(name="product_size",joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name="size_id"))
	private List<SizeEntity> sizes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id",referencedColumnName = "id")
	private CategoryEntity category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_gender",referencedColumnName = "id")
	private CategoryGenderEntity categoryGender;

	@ManyToOne
	@JoinColumn(name = "shop_id",referencedColumnName = "id")
	private ShopEntity shop;

	public void setShop(ShopEntity shop) {
		this.shop = shop;
	}

	public ShopEntity getShop() {
		return shop;
	}

	public CategoryGenderEntity getCategoryGender() {
		return categoryGender;
	}

	public void setCategoryGender(CategoryGenderEntity categoryGender) {
		this.categoryGender = categoryGender;
	}

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

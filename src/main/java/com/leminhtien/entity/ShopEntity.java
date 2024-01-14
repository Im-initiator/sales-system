package com.leminhtien.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop")
public class ShopEntity extends BaseEntity{
    @Column
    private float reviews;
    @Column
    private String name;
    @Column
    private String thumbnail;
    @Column(name = "short_description",columnDefinition = "TEXT")
    private String shortDescription;
    @Column
    private String link;

    @Column
    private String address;

    @OneToOne(mappedBy = "shop")
    private UserEntity user;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ProductEntity> products;

      @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
      @JoinTable(name = "category_shop",joinColumns = @JoinColumn(name = "shop_id"),inverseJoinColumns = @JoinColumn(name = "category_id"))
      private List<CategoryEntity> categories;

    @ManyToMany(mappedBy = "shops",fetch = FetchType.LAZY)
    private List<CategoryGenderEntity> categoryGenderEntities;



    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "size_shop",joinColumns = @JoinColumn(name = "shop_id"),inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<SizeEntity> sizes;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public List<CategoryGenderEntity> getCategoryGenderEntities() {
        return categoryGenderEntities;
    }

    public void setCategoryGenderEntities(List<CategoryGenderEntity> categoryGenderEntities) {
        this.categoryGenderEntities = categoryGenderEntities;
    }

    public float getReviews() {
        return reviews;
    }

    public void setReviews(float reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLink() {
        return link;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public List<SizeEntity> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeEntity> sizes) {
        this.sizes = sizes;
    }
}

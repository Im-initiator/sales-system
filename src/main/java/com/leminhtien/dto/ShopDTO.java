package com.leminhtien.dto;

import com.leminhtien.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

public class ShopDTO extends BaseDTO{
    private float reviews;
    private String name;
    private String thumbnail;
    private MultipartFile imgThumbnail;
    private String shortDescription;
    private String link;
    private String address;
    private String phoneNumber;
    private String email;

    public List<SizeDTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeDTO> sizes) {
        this.sizes = sizes;
    }

    private List<SizeDTO> sizes;

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryGenderDTO> getCategoryGenderEntities() {
        return categoryGenderEntities;
    }

    public void setCategoryGenderEntities(List<CategoryGenderDTO> categoryGenderEntities) {
        this.categoryGenderEntities = categoryGenderEntities;
    }

    private List<CategoryDTO> categories;
    private List<CategoryGenderDTO> categoryGenderEntities;

    private int items;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Long userId;

    private List<ProductDTO> products;

    public MultipartFile getImgThumbnail() {
        return imgThumbnail;
    }

    public void setImgThumbnail(MultipartFile imgThumbnail) {
        this.imgThumbnail = imgThumbnail;
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

    public void setLink(String link) {
        this.link = link;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

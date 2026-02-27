package com.pm.studentmarketplace.listing.model;


import com.pm.studentmarketplace.auth.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private String category;

    private Double price;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private String imagePaths;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false) // indicates there can be many listings per seller
    @JoinColumn(name = "user_id")
    private User seller;

    protected Listing() {
    }

    public Listing(String title, String description, String category, Double price, String contactInfo, User seller) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.contactInfo = contactInfo;
        this.imagePaths = "C:/Users/sonjy/OneDrive/Documents/Github/studentmarketplace/images/";
        this.status = "ACTIVE";
        this.createdAt = LocalDateTime.now();
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getImagePaths() {
        return imagePaths;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getSeller() {
        return seller;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void markAsSold() {
        this.status = "SOLD";
    }
}

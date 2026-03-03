package com.pm.studentmarketplace.listing.model;


import com.pm.studentmarketplace.auth.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "listings")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private String category;

    private Double price;

    @Column(nullable = false)
    private String contactInfo;

    @OneToMany(
            mappedBy = "listing",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ListingImage> images = new ArrayList<>();

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false) // indicates there can be many listings per seller
    @JoinColumn(name = "user_id")
    private User seller;

    public Listing() {
    }

    public Listing(String title, String description, String category, Double price, String contactInfo, User seller) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.contactInfo = contactInfo;
        this.status = "ACTIVE";
        this.seller = seller;
    }

    public Long getId() {
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getSeller() {
        return seller;
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

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public void markAsSold() {
        this.status = "SOLD";
    }

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void addImage(ListingImage image) {
        images.add(image);
    }

    public void clearImages() {
        images.clear();
    }

    public List<ListingImage> getImages() {
        return images;
    }
}

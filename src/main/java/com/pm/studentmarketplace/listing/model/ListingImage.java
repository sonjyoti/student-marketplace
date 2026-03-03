package com.pm.studentmarketplace.listing.model;

import jakarta.persistence.*;

@Entity
@Table(name = "listing_images")
public class ListingImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String imagePath;

    @ManyToOne(optional = false)
    @JoinColumn(name = "listing_id")
    private Listing listing;

    protected ListingImage() {}

    public ListingImage(String imagePath, Listing listing) {
        this.imagePath = imagePath;
        this.listing = listing;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Listing getListing() {
        return listing;
    }
}

package com.pm.studentmarketplace.listing.repository;

import com.pm.studentmarketplace.listing.model.ListingImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingImageRepository extends JpaRepository<ListingImage, Long> {
}

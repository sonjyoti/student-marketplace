package com.pm.studentmarketplace.listing.repository;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.listing.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findBySeller(User user);
    List<Listing> findByStatus(String status);
}

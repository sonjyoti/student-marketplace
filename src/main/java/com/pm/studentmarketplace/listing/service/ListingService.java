package com.pm.studentmarketplace.listing.service;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.repository.ListingRepository;
import org.springframework.stereotype.Service;

@Service
public class ListingService {
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Listing createListing(
            String title,
            String description,
            Double price,
            String contactInfo,
            User seller) {
        Listing listing = new Listing(
                title,
                description,
                null,
                price,
                contactInfo,
                seller
                );

        return listingRepository.save(listing);
    }
}

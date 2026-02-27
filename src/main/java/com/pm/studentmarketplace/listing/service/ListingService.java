package com.pm.studentmarketplace.listing.service;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void approveListing(Long listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("listing not found!!"));
        listing.setStatus("APPROVED");
        listingRepository.save(listing);
    }

    public void blockListing(Long listingId) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("listing not found!!"));
        listing.setStatus("BLOCKED");
        listingRepository.save(listing);
    }

    // read listing by given seller
    public List<Listing> getListingsBySeller(User seller) {
        return listingRepository.findBySeller(seller);
    }
}

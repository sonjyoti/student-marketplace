package com.pm.studentmarketplace.listing.service;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.repository.ListingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public void createListing(
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

//        return listingRepository.save(listing);
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

    public Listing getListingById(Long listingId) {
        return listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("listing not found!!"));
    }

    public void updateListing(Long listingId,
                              String title,
                              String description,
                              Double price,
                              String contactInfo,
                              User seller
    ) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found!!"));

        System.out.println("Listing sellerID: " + listing.getSeller().getId());
        System.out.println("Logged in sellerID: " + seller.getId());

        // owner check
        if (!listing.getSeller().getId().equals(seller.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Unauthorized!"
            );
        }

        listing.setTitle(title);
        listing.setDescription(description);
        listing.setPrice(price);
        listing.setContactInfo(contactInfo);

        // reset status so admin can re-approve
        listing.setStatus("ACTIVE");

        listingRepository.save(listing);

    }

    public void deleteListing(Long listingId, User seller) {
        Listing listing = listingRepository.findById(listingId)
                .orElseThrow(() -> new RuntimeException("Listing not found!!"));

        if (!listing.getSeller().getId().equals(seller.getId())) {
            throw new RuntimeException("Unauthorized Access!!!");
        }

        listingRepository.delete(listing);
    }

    // read listing by given seller
    public List<Listing> getListingsBySeller(User seller) {
        return listingRepository.findBySeller(seller);
    }

    public List<Listing> getApprovedListings() {
        return listingRepository.findByStatus("APPROVED");
    }
}

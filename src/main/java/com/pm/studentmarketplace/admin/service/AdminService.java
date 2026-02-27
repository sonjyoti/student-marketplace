package com.pm.studentmarketplace.admin.service;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.auth.repository.UserRepository;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    public AdminService(UserRepository userRepository, ListingRepository listingRepository) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
    }

    public long getTotalUsers() {
        return userRepository.count();
    }

    public long getTotalListings() {
        return listingRepository.count();
    }

    public void approveListing(Long listingId){
        listingRepository.findById(listingId)
                .ifPresent(listing -> {
                    listing.setStatus("APPROVED");
                    listingRepository.save(listing);
                });
    }

    public void blockListing(Long listingId){
        listingRepository.findById(listingId)
                .ifPresent(listing -> {
                    listing.setStatus("BLOCKED");
                    listingRepository.save(listing);
                });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }
}

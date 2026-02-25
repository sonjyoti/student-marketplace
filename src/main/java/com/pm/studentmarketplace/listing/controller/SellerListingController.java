package com.pm.studentmarketplace.listing.controller;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.auth.repository.UserRepository;
import com.pm.studentmarketplace.listing.service.ListingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("seller/listings")
public class SellerListingController {
    private final ListingService listingService;
    private final UserRepository userRepository;

    public SellerListingController(ListingService listingService, UserRepository userRepository) {
        this.listingService = listingService;
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public String showCreateForm(){
        return "seller/create-listing";
    }

    @PostMapping("/new")
    public String createListing(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam String contactInfo,
            Authentication authentication
    ){
        String email = authentication.getName();
        User seller = userRepository.findByEmail(email).orElseThrow();

        listingService.createListing(title, description, price, contactInfo, seller);

        return "redirect:/seller/dashboard";
    }
}

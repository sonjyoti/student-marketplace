package com.pm.studentmarketplace.listing.controller;

import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.service.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicListingController {

    private final ListingService listingService;

    public PublicListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/listing/{id}")
    public String viewListing(
            Long id,
            Model model
    ){
        Listing listing = listingService.getListingById(id);

        model.addAttribute("listing", listing);

        return "public/listing-details";
    }
}

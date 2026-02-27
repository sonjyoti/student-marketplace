package com.pm.studentmarketplace.publicview;

import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.service.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MarketplaceController {
    private final ListingService listingService;

    public MarketplaceController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/marketplace")
    public String marketplace(Model model) {
        List<Listing> listings = listingService.getApprovedListings();
        model.addAttribute("listings", listings);

        return "public/marketplace";
    }
}

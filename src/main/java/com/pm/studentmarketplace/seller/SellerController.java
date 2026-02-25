package com.pm.studentmarketplace.seller;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.auth.repository.UserRepository;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.service.ListingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {
    private final ListingService listingService;
    private final UserRepository userRepository;

    public SellerController(ListingService listingService, UserRepository userRepository) {
        this.listingService = listingService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        User seller = userRepository.findByEmail(email).orElseThrow();

        List<Listing> listings = listingService.getListingsBySeller(seller);

        model.addAttribute("listings", listings);
        return "seller/dashboard";
    }
}

// Model is used to display backend data into an HTML file
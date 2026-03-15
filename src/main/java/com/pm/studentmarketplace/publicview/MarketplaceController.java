package com.pm.studentmarketplace.publicview;

import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.service.ListingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MarketplaceController {
    private final ListingService listingService;

    public MarketplaceController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("/marketplace")
    public String marketplace(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "newest") String sort,
            Model model) {
        int pageSize = 6;

        Pageable pageable = switch (sort) {
            case "price_asc" -> PageRequest.of(page, pageSize, Sort.by("price").ascending());
            case "price_desc" -> PageRequest.of(page, pageSize, Sort.by("price").descending());
            default -> PageRequest.of(page, pageSize, Sort.by("createdAt").descending());
        };

        Page<Listing> listingPage = listingService.searchMarketplace(keyword, category, pageable);

        model.addAttribute("listings", listingPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", listingPage.getTotalPages());

        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        model.addAttribute("sort", sort);

        return "/public/marketplace2";
    }
}

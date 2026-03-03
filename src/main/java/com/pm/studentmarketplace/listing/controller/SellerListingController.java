package com.pm.studentmarketplace.listing.controller;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.auth.repository.UserRepository;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.model.ListingImage;
import com.pm.studentmarketplace.listing.service.ImageStorageService;
import com.pm.studentmarketplace.listing.service.ListingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("seller/listings")
public class SellerListingController {
    private final ListingService listingService;
    private final UserRepository userRepository;
    private final ImageStorageService imageStorageService;

    public SellerListingController(ListingService listingService, UserRepository userRepository, ImageStorageService imageStorageService) {
        this.listingService = listingService;
        this.userRepository = userRepository;
        this.imageStorageService = imageStorageService;
    }

    // ----CREATE LISTING----

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
            @RequestParam MultipartFile[] images,
            Authentication authentication
    ){
        String email = authentication.getName();
        User seller = userRepository.findByEmail(email).orElseThrow();

        Listing listing = new Listing();
        listing.setTitle(title);
        listing.setDescription(description);
        listing.setPrice(price);
        listing.setContactInfo(contactInfo);
        listing.setSeller(seller);
        listing.setStatus("ACTIVE");

        if (images != null){
            for (MultipartFile file : images){
                if (!file.isEmpty()){
                    String path = imageStorageService.store(file);
                    listing.addImage(new ListingImage(path, listing));
                }
            }
        }

        listingService.save(listing);

        return "redirect:/seller/dashboard";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id,
                               Authentication authentication,
                               Model model
    ){
        String email = authentication.getName();
        User seller = userRepository.findByEmail(email).orElseThrow();

        Listing listing = listingService.getListingById(id);

        model.addAttribute("listing", listing);

        return "seller/edit-listing";
    }

    @PostMapping("/{id}/edit")
    public String updateListing(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) Double price,
            @RequestParam String contactInfo,
            @RequestParam(required = false, value = "images") MultipartFile[] images,
            Authentication authentication
    ){
        String email = authentication.getName();
        User seller = userRepository.findByEmail(email).orElseThrow();

        listingService.updateListing(
                id, title, description, price, contactInfo, images, seller
        );

        return "redirect:/seller/dashboard";
    }

    @PostMapping("/{id}/delete")
    public String deleteListing(@PathVariable Long id, Authentication authentication){
        String email = authentication.getName();
        User seller = userRepository.findByEmail(email).orElseThrow();

        listingService.deleteListing(id, seller);

        return "redirect:/seller/dashboard";
    }

}

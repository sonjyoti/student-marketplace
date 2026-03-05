package com.pm.studentmarketplace.listing.service;

import com.pm.studentmarketplace.auth.model.User;
import com.pm.studentmarketplace.listing.model.Listing;
import com.pm.studentmarketplace.listing.model.ListingImage;
import com.pm.studentmarketplace.listing.repository.ListingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final ImageStorageService imageStorageService;

    public ListingService(ListingRepository listingRepository, ImageStorageService imageStorageService) {
        this.listingRepository = listingRepository;
        this.imageStorageService = imageStorageService;
    }

    public void save(Listing listing) {
        listingRepository.save(listing);
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
                              MultipartFile[] images,
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

        if (images != null && images.length > 0) {
            int validImages = 0;

            for (MultipartFile file : images) {
                if (file != null && !file.isEmpty()) {
                    validImages++;
                }
            }

            if (images.length > validImages) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum 3 images allowed");
            }

            // delete old images
            for (ListingImage oldImage : listing.getImages()) {
                imageStorageService.delete(oldImage.getImagePath());
            }

            // remove old image records (orphan removal is handled by db)
            listing.clearImages();

            //stores new images
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    String path = imageStorageService.store(file);
                    listing.addImage(new ListingImage(path, listing));
                }
            }
        }

        System.out.println("Images received: " + (images == null ? "null" : images.length));

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

    public Page<Listing> searchMarketplace(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return listingRepository.findByStatus("APPROVED", pageable);
        }

        return listingRepository.findByStatusAndTitleContainingIgnoreCase("APPROVED", keyword, pageable);
    }

    // read listing by given seller
    public List<Listing> getListingsBySeller(User seller) {
        return listingRepository.findBySeller(seller);
    }

    public List<Listing> getApprovedListings() {
        return listingRepository.findByStatus("APPROVED");
    }
}

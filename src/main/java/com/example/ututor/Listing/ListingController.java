package com.example.ututor.Listing;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public List<Listing> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/{id}")
    public Optional<Listing> getListing(@PathVariable Long id) { // @PathVariable extracts id from the url
        return listingService.getListingById(id);
    }

    @GetMapping("/{tutorId}")
    public List<Listing> getListingFromlisting(@PathVariable Long tutorId) { // @PathVariable extracts id from the url
        return listingService.getListingsByTutorId(tutorId);
    }

    @PostMapping
    public Listing createlisting(@RequestBody Listing listing) {
        return listingService.saveListing(listing);
    }

    @PutMapping("/{id}")
    public Listing updatelisting(@PathVariable Long id, @RequestBody Listing listing) {
        listing.setId(id);
        return listingService.saveListing(listing);
    }

    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable Long id) {
        listingService.deleteListing(id);
    }
}

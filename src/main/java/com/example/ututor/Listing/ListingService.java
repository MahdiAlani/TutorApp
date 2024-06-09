package com.example.ututor.Listing;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public Optional<Listing> getListingById(Long id) {
        return listingRepository.findById(id);
    }

    public List<Listing> getListingsByTutorId(Long tutorId) {
        return listingRepository.findByTutorId(tutorId);
    }

    public Listing saveListing(Listing listing) {
        return listingRepository.save(listing);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }
}

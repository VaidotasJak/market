package com.skydive.market.service;

import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.ListingModelDTOMapper;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import com.skydive.market.model.mapper.ListingModelMapper;
import com.skydive.market.model.mapper.RegistrationModelMapper;
import com.skydive.market.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;
    private final ListingModelMapper listingModelMapper;

    public List<Listing> getAllListings(final RegistrationCreationDTO dto) {
        List<Listing> listings = listingRepository.fetchAllListings(dto);
        if(listings.isEmpty()){
            return new ArrayList<>();
        }
        return listings;
    }

    public Listing createListing(ListingModelDTO listingModelDTO, Registration registration) {
        Listing listing = listingModelMapper.mapToModel(listingModelDTO);
        listing.setRegistration(registration);
        return listingRepository.save(listing);
    }

    public Listing assignListing(Listing listing) {
        return listingRepository.save(listing);
    }


}

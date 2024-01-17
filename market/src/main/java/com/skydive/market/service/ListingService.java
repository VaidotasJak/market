package com.skydive.market.service;

import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import com.skydive.market.model.mapper.ListingModelMapper;
import com.skydive.market.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListingService {
    private final ListingRepository listingRepository;
    private final ListingModelMapper listingModelMapper;

    public List<ListingDto> getAllListings(final RegistrationCreationDTO dto) {
        List<ListingDto> listings = listingRepository.fetchAllListings(dto);
        if(listings.isEmpty()){
            return new ArrayList<>();
        }
        return listings;
    }


    @Transactional
    public Listing createListing(ListingModelDTO listingModelDTO, Registration registration) {
        Listing listing = listingModelMapper.mapToModel(listingModelDTO);
        listing.setRegistration(registration);
        return listingRepository.save(listing);
    }

    public Listing assignListing(Listing listing) {
        return listingRepository.save(listing);
    }


}

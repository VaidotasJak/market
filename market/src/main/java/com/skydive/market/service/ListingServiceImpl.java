package com.skydive.market.service;

import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.exceptions.NoSuchListingException;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import com.skydive.market.model.mapper.ListingModelMapper;
import com.skydive.market.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService{
    private final ListingRepository listingRepository;
    private final ListingModelMapper listingModelMapper;
    @Override
    public List<ListingDto> getAllAvailable(final RegistrationCreationDTO dto) {
        List<ListingDto> listings = listingRepository.fetchAllAvailableListings(dto);
        if(listings.isEmpty()){
            return new ArrayList<>();
        }
        return listings;
    }
    @Override
    public List<ListingDto> getAllAvailable() {
        List<ListingDto> listings = listingRepository.fetchAllAvailableListings();
        if(listings.isEmpty()){
            return new ArrayList<>();
        }
        return listings;
    }

    @Transactional
    @Override
    public Listing create(ListingModelDTO listingModelDTO, Registration registration) {
        Listing listing = listingModelMapper.mapToModel(listingModelDTO);
        listing.setRegistration(registration);
        return listingRepository.save(listing);
    }

    @Override
    public void delete(Long id, Registration registration) {
        List<ListingDto> listingDtos = listingRepository.fetch(id);
        if(listingDtos.isEmpty()) {
            throw new NoSuchListingException("Listing you were trying to delete, does not exist.");
        }
        /**
         * NEED TO ADD VALIDATION IF LISTING DOES NOT BELONG TO USER
         */
        Map<String, Object> list = (Map<String, Object>) listingDtos.get(0);
        Listing listing = listingModelMapper.mapToListing(list);
        listing.setId(id);
        listing.setRegistration(registration);
        listingRepository.delete(listing);
    }

}

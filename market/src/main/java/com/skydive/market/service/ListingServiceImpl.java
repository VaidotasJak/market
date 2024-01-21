package com.skydive.market.service;

import com.skydive.market.dto.ListingAllDto;
import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.ListingModelDTOMapper;
import com.skydive.market.exceptions.ListingDoesNotBelongsToRegistrationException;
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
    private final ListingModelDTOMapper listingModelDTOMapper;
    private final RegistrationService registrationService;


    @Override
    public List<ListingDto> getAllAvailable(final RegistrationCreationDTO dto) {
        registrationService.getRegistration(dto.getId().intValue());
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

    public List<ListingAllDto> generateListing(List<ListingDto> listingDtos) {
        List<ListingAllDto> listingAllDtos = new ArrayList<>();
        for (ListingDto listingDto : listingDtos) {
            listingAllDtos.add(listingModelDTOMapper.fromListingDtoListAllDto(listingDto));
        }
        return listingAllDtos;
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

        if(!userCanDeleteListing(id, registration)){
            throw new ListingDoesNotBelongsToRegistrationException("Listing with id: " + id + " does not belongs to registrations id:" + registration.getId());
        }

        ListingDto listingDto = listingDtos.get(0);
        Listing listing = listingModelMapper.mapToListing(listingDto);
        listing.setId(id);
        listing.setRegistration(registration);
        listingRepository.delete(listing);
    }

    private boolean userCanDeleteListing(Long id, Registration registration){
        RegistrationCreationDTO registrationCreationDTO = new RegistrationCreationDTO();
        registrationCreationDTO.setId(registration.getId());
        List<ListingDto> listingDtos = getAllAvailable(registrationCreationDTO);

        ListingDto listing = listingDtos.stream().filter(listingDto -> listingDto.getId().longValue() == id).findFirst().orElse(null);
        if(listing == null) {
            return false;
        }
        return true;
    }

}

package com.skydive.market.dto.mapper;

import com.skydive.market.controller.request.ListingRequest;
import com.skydive.market.dto.ListingAllDto;
import com.skydive.market.dto.ListingCreationDto;
import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.enums.ListingStatus;
import org.springframework.stereotype.Service;

@Service
public class ListingModelDTOMapper {
    public ListingModelDTO mapToModel(final ListingRequest listingRequest) {
        return new ListingModelDTO()
                .setName(listingRequest.getName())
                .setDescription(listingRequest.getDescription())
                .setWeight(listingRequest.getWeight())
                .setPrice(listingRequest.getPrice());
    }

    public ListingCreationDto fromListing(final Listing listing) {
        return new ListingCreationDto()
                .setId(listing.getId())
                .setName(listing.getName())
                .setDescription(listing.getDescription())
                .setWeight(listing.getWeight())
                .setPrice(listing.getPrice())
                .setListingStatus(listing.getListingStatus())
                .setRegistrationId(listing.getRegistration().getId());
    }

    public ListingAllDto fromListingDtoListAllDto(final ListingDto listingDto) {
        return new ListingAllDto()
                .setId(listingDto.getId().longValue())
                .setName(listingDto.getName())
                .setDescription(listingDto.getDescription())
                .setWeight(listingDto.getWeight())
                .setPrice(listingDto.getPrice())
                .setListingStatus(listingDto.getListingstatus().equals(1) ? ListingStatus.AVAILABLE : ListingStatus.SOLD);
    }


}

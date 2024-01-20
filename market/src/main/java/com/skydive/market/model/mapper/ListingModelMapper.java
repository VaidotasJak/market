package com.skydive.market.model.mapper;

import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import com.skydive.market.model.enums.ListingStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ListingModelMapper {
    public Listing mapToModel(ListingModelDTO dto) {
        return new Listing()
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setWeight(dto.getWeight())
                .setPrice(dto.getPrice())
                .setListingStatus(ListingStatus.AVAILABLE);
    }

    public Listing mapToListing(ListingDto listingDto) {
        return new Listing()
                .setName(listingDto.getName())
                .setDescription(listingDto.getDescription())
                .setWeight(listingDto.getWeight())
                .setPrice(listingDto.getPrice())
                .setListingStatus(listingDto.getListingstatus().equals(1) ? ListingStatus.AVAILABLE : ListingStatus.SOLD);

    }

}
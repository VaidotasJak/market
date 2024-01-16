package com.skydive.market.dto.mapper;

import com.skydive.market.controller.request.ListingRequest;
import com.skydive.market.dto.ListingModelDTO;
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
}

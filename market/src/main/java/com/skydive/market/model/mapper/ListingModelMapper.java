package com.skydive.market.model.mapper;

import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.enums.ListingStatus;
import org.springframework.stereotype.Service;

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
}
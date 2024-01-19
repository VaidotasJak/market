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

    public Listing mapToListing(Map<String, Object> list) {
        Listing listing = new Listing();
        for (String s : list.keySet()) {
            Object value = list.get(s);
            System.out.println("Key: " + s + ", Value: " + value);
            if (s.equals("name")) {
                listing.setName((String) value);
            }
            if (s.equals("description")) {
                listing.setDescription((String) value);
            }
            if (s.equals("weight")) {
                listing.setWeight((Double) value);
            }
            if (s.equals("price")) {
                listing.setPrice((Double) value);
            }
            if (s.equals("listingstatus")) {
                listing.setListingStatus(value.equals(1) ? ListingStatus.AVAILABLE : ListingStatus.SOLD);
            }
        }
        return listing;
    }

}
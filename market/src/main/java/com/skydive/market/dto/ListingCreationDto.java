package com.skydive.market.dto;

import com.skydive.market.model.enums.ListingStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ListingCreationDto {
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double price;
    private ListingStatus listingStatus;
    private Long registrationId;
}

package com.skydive.market.model.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingDTO {

    private String name;
    private String description;
    private Double weight;
    private Double price;
}
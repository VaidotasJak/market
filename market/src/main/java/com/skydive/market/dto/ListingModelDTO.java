package com.skydive.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ListingModelDTO {
    private String name;
    private String description;
    private Double weight;
    private Double price;
}

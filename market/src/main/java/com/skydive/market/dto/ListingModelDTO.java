package com.skydive.market.dto;

import com.skydive.market.model.enums.ListingStatus;
import com.skydive.market.model.enums.SportType;
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

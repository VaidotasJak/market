package com.skydive.market.model;

import com.skydive.market.model.enums.ListingStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ListingDto {
    private BigInteger id;
    private String name;
    private String description;
    private Double weight;
    private Double price;
    private Integer listingstatus;
}
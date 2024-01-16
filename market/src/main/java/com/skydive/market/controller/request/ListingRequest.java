package com.skydive.market.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListingRequest {

        private String name;
        private String description;
        private Double weight;
        private Double price;
}

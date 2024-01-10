package com.skydive.market.model.listing;

import com.skydive.market.model.ListingStatus;
import com.skydive.market.model.registration.Registration;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double price;
    private ListingStatus listingStatus;
    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;
}

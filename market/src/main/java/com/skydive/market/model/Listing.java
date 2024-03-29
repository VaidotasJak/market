package com.skydive.market.model;

import com.skydive.market.model.enums.ListingStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double weight;
    private Double price;
    @Column(name="listingStatus")
    private ListingStatus listingStatus;
    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;
}

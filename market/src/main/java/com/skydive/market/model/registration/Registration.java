package com.skydive.market.model.registration;

import com.skydive.market.model.SportType;
import com.skydive.market.model.listing.Listing;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String dob;
    private String email;
    private Long phoneNumber;
    private String country;
    private boolean isSportsMan;
    private SportType typeOfSport;
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Listing> listings;
    private boolean belongsToGroup;

}

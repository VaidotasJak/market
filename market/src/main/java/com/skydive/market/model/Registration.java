package com.skydive.market.model;

import com.skydive.market.model.enums.SportType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    private String password;
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

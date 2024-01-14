package com.skydive.market.dto;

import com.skydive.market.model.Listing;
import com.skydive.market.model.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RegistrationModelDTO {
    private String name;
    private String lastName;
    private String password;
    private String dob;
    private String email;
    private Long phoneNumber;
    private String country;
    private boolean isSportsMan;
    private SportType typeOfSport;
}

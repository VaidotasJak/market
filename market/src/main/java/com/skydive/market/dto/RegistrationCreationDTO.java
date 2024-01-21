package com.skydive.market.dto;

import com.skydive.market.model.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Accessors(chain = true)
public class RegistrationCreationDTO {
    private Long id;
    private String name;
    private String lastName;
    private String dob;
    private String email;
    private Long phoneNumber;
    private String country;
    private boolean isSportsMan;
    private SportType typeOfSport;
    private boolean belongsToGroup;

}

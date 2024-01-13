package com.skydive.market.controller.request;

import com.skydive.market.model.enums.SportType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

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

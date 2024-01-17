package com.skydive.market.dto.mapper;

import com.skydive.market.controller.request.LoginRequest;
import com.skydive.market.dto.LoginModelDTO;
import com.skydive.market.dto.LoginSuccessDTO;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginModelDTOMapper {
    public LoginModelDTO mapToModel(final LoginRequest loginRequest) {
        return new LoginModelDTO()
                .setEmail(loginRequest.getEmail())
                .setPassword(loginRequest.getPassword());
    }

    public LoginSuccessDTO fromExistingRegistration(final Registration registration, final List<ListingDto> listings) {
        return new LoginSuccessDTO()
                .setId(registration.getId())
                .setName(registration.getName())
                .setLastName(registration.getLastName())
                .setDob(registration.getDob())
                .setEmail(registration.getEmail())
                .setPhoneNumber(registration.getPhoneNumber())
                .setCountry(registration.getCountry())
                .setSportsMan(registration.isSportsMan())
                .setTypeOfSport(registration.getTypeOfSport())
                .setBelongsToGroup(registration.isBelongsToGroup())
                .setListings(listings);
    }

}

package com.skydive.market.dto.mapper;

import com.skydive.market.controller.request.RegistrationRequest;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.model.Registration;
import org.springframework.stereotype.Service;

@Service
public class RegistrationModelDTOMapper {
    public RegistrationModelDTO mapToModel(final RegistrationRequest registrationRequest) {
        return new RegistrationModelDTO()
                .setName(registrationRequest.getName())
                .setLastName(registrationRequest.getLastName())
                .setPassword(registrationRequest.getPassword())
                .setDob(registrationRequest.getDob())
                .setEmail(registrationRequest.getEmail())
                .setPhoneNumber(registrationRequest.getPhoneNumber())
                .setCountry(registrationRequest.getCountry())
                .setSportsMan(registrationRequest.getIsSportsMan())
                .setTypeOfSport(registrationRequest.getTypeOfSport());
    }

    public RegistrationCreationDTO fromRegistration(final Registration registration) {
        return new RegistrationCreationDTO()
                .setId(registration.getId())
                .setName(registration.getName())
                .setLastName(registration.getLastName())
                .setDob(registration.getDob())
                .setEmail(registration.getEmail())
                .setPhoneNumber(registration.getPhoneNumber())
                .setCountry(registration.getCountry())
                .setSportsMan(registration.isSportsMan())
                .setTypeOfSport(registration.getTypeOfSport())
                .setBelongsToGroup(registration.isBelongsToGroup());
    }
}

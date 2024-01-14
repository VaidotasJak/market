package com.skydive.market.model.mapper;

import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationModelMapper {
    public Registration mapToModel(RegistrationModelDTO dto) {
        List<Listing> emptyList = new ArrayList<>();
        return new Registration()
                .setName(dto.getName())
                .setLastName(dto.getLastName())
                .setPassword(dto.getPassword())
                .setDob(dto.getDob())
                .setEmail(dto.getEmail())
                .setPhoneNumber(dto.getPhoneNumber())
                .setCountry(dto.getCountry())
                .setSportsMan(dto.isSportsMan())
                .setTypeOfSport(dto.getTypeOfSport())
                .setListings(emptyList);
    }
}
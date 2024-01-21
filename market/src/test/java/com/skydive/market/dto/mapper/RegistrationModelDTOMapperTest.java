package com.skydive.market.dto.mapper;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.model.Registration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationModelDTOMapperTest {

    @Test
    void fromRegistration() {

        RegistrationModelDTOMapper mapper = new RegistrationModelDTOMapper();

        Registration registration = Registration.builder().name("Pirmas").password("Saugus").build();
        var registrationCreationDTO = mapper.fromRegistration(registration);

        assertEquals("Pirmas", registrationCreationDTO.getName());
    }
}
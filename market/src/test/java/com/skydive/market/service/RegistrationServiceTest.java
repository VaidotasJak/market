package com.skydive.market.service;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.model.Registration;
import com.skydive.market.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
class RegistrationServiceTest {
    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private RegistrationModelDTOMapper registrationModelDTOMapper;
    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void getAllRegistrations() {
        List<Registration> registrationList = new ArrayList<>();
        RegistrationCreationDTO registrationCreationDTOS = RegistrationCreationDTO.builder().id(1L).name("Pirmas").build();

        Registration registration = Registration.builder().id(1L).name("Pirmas").build();
        Registration registration2 = Registration.builder().id(2L).name("Antras").build();
        registrationList.add(registration);
        registrationList.add(registration2);

        // Mock the repository behavior
        when(registrationRepository.getAllRegistrations()).thenReturn(registrationList);
        when(registrationModelDTOMapper.fromRegistration(any())).thenReturn(registrationCreationDTOS);

        List<RegistrationCreationDTO> allRegistrations = registrationService.getAllRegistrations();

        assertEquals(2, allRegistrations.size());
    }

}
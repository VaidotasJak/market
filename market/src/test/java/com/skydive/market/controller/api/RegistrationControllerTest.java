package com.skydive.market.controller.api;

import com.skydive.market.controller.request.RegistrationRequest;
import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.model.Registration;
import com.skydive.market.model.enums.SportType;
import com.skydive.market.repository.RegistrationRepository;
import com.skydive.market.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)

class RegistrationControllerTest {
    @Mock
    private RegistrationService registrationService;
    @Mock
    private RegistrationRepository registrationRepository;


    @InjectMocks
    private RegistrationController registrationController;
    @InjectMocks
    private RegistrationModelDTOMapper registrationModelDTOMapper;

    @Test
    void testRegisterUser_SuccessfulRegistration() {

        RegistrationRequest request = new RegistrationRequest("Sigis", "Skydiveris", "Labas123",
                "1994-11-01", "sigis@gmail.com", 78987541651L, "Lietuva", true, SportType.BASEJUMP);

        RegistrationModelDTO registrationModelDTO = registrationModelDTOMapper.mapToModel(request);
    }


}
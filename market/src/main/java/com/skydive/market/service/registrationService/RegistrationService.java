package com.skydive.market.service.registrationService;

import com.skydive.market.model.registration.Registration;
import com.skydive.market.repository.RegistrationRepository;

import java.util.List;

public class RegistrationService {
    public void saveAll(List<Registration> registrations){
        RegistrationRepository registrationRepository = new RegistrationRepository();
        registrationRepository.saveAll(registrations);
    }

}

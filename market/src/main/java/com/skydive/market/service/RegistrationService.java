package com.skydive.market.service;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.exceptions.RegistrationAlreadyExistsException;
import com.skydive.market.model.Registration;
import com.skydive.market.model.mapper.RegistrationModelMapper;
import com.skydive.market.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public void saveAll(List<Registration> registrations) {
        RegistrationRepository registrationRepository = new RegistrationRepository();
        registrationRepository.saveAll(registrations);
    }

    public Registration registerNewUser(RegistrationModelDTO dto) {
        List<Registration> existingRegistrations = registrationRepository.fetchData(dto.getEmail(), dto.getPhoneNumber());
        if (!existingRegistrations.isEmpty()) {
            throw new RegistrationAlreadyExistsException("User with this email, or phone number already exist.");
        }
        return registrationRepository.save(new
                RegistrationModelMapper().mapToModel(dto));
    }

    public List<RegistrationCreationDTO> getAllRegistrations() {
        List<Registration> registrationList = registrationRepository.getAllRegistrations();
        List<RegistrationCreationDTO> registrationCreationDTOS = new ArrayList<>();

        for (Registration registration : registrationList) {
            registrationCreationDTOS.add(new RegistrationModelDTOMapper().fromRegistration(registration));
        }
        return registrationCreationDTOS;
    }


}

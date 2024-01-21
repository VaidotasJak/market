package com.skydive.market.service;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.exceptions.NoSuchUserException;
import com.skydive.market.exceptions.RegistrationAlreadyExistsException;
import com.skydive.market.model.Registration;
import com.skydive.market.model.mapper.RegistrationModelMapper;
import com.skydive.market.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationModelDTOMapper registrationModelDTOMapper;
    private final RegistrationModelMapper registrationModelMapper;

    public Registration registerNewUser(RegistrationModelDTO dto) {
        List<Registration> existingRegistrations = registrationRepository.fetchData(dto);
        if (!existingRegistrations.isEmpty()) {
            throw new RegistrationAlreadyExistsException("User with this email, or phone number already exist.");
        }
        return registrationRepository.save(registrationModelMapper.mapToModel(dto));
    }

    public List<RegistrationCreationDTO> getAllRegistrations() {
        List<Registration> registrationList = registrationRepository.getAllRegistrations();
        List<RegistrationCreationDTO> registrationCreationDTOS = new ArrayList<>();
        registrationList.forEach(registration -> registrationCreationDTOS.add(registrationModelDTOMapper.fromRegistration(registration)));
        return registrationCreationDTOS;
    }

    public Registration getRegistration(Integer userId) {
        Registration registrationList = registrationRepository.getRegistration(userId);
        if (registrationList == null) {
            throw new NoSuchUserException("User with id: " + userId + " does not exists.");
        }
        return registrationList;
    }

}

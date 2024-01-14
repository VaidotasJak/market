package com.skydive.market.service;

import com.skydive.market.dto.LoginModelDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.exceptions.IncorrectPasswordException;
import com.skydive.market.exceptions.NoSuchUserException;
import com.skydive.market.exceptions.RegistrationAlreadyExistsException;
import com.skydive.market.model.Registration;
import com.skydive.market.model.mapper.RegistrationModelMapper;
import com.skydive.market.repository.LoginRepository;
import com.skydive.market.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;
//    private final RegistrationModelDTOMapper registrationModelDTOMapper;
//    private final RegistrationModelMapper registrationModelMapper;

    public Registration login(LoginModelDTO dto) {
        Registration registration;
        List<Registration> existingRegistration = loginRepository.fetchDataByEmail(dto);

        if (userInList(existingRegistration, dto)) {
            throw new NoSuchUserException("Could not find user with email: " + dto.getEmail() + ".");
        }
        return passwordMatches(existingRegistration, dto);
    }

    private boolean userInList(List<Registration> existingRegistration, LoginModelDTO dto){
        return !existingRegistration.stream().anyMatch(registration -> registration.getEmail().equals(dto.getEmail()));
    }

    private Registration passwordMatches(List<Registration> existingRegistration, LoginModelDTO dto){
        Registration existingRegistration1 =
                existingRegistration.stream().filter(registration -> registration.getEmail().equals(dto.getEmail())).findFirst().get();
        if(existingRegistration1.getPassword().equals(dto.getPassword())){
            return existingRegistration1;
        }
        throw new IncorrectPasswordException("Password was not correct");
    };


}

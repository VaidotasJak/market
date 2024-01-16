package com.skydive.market.service;

import com.skydive.market.dto.LoginModelDTO;
import com.skydive.market.exceptions.IncorrectPasswordException;
import com.skydive.market.exceptions.NoSuchUserException;
import com.skydive.market.model.Registration;
import com.skydive.market.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginRepository loginRepository;

    public Registration login(LoginModelDTO dto) {
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

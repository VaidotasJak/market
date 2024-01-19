package com.skydive.market.controller.api;

import com.skydive.market.controller.request.LoginRequest;
import com.skydive.market.dto.LoginSuccessDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.LoginModelDTOMapper;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import com.skydive.market.service.ListingServiceImpl;
import com.skydive.market.service.LoginService;
import com.skydive.market.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;
    private final RegistrationService registrationService;
    private final ListingServiceImpl listingServiceImpl;
    private final LoginModelDTOMapper loginModelDTOMapper;
    private final RegistrationModelDTOMapper registrationModelDTOMapper;

    @PostMapping(name = "login", value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginSuccessDTO> login(@RequestBody final LoginRequest loginRequest) {
        Registration existingRegistration = loginService.login(loginModelDTOMapper.mapToModel(loginRequest));

        RegistrationCreationDTO registrationCreationDTO = registrationModelDTOMapper.fromRegistration(existingRegistration);
        List<ListingDto> userListings = listingServiceImpl.getAllAvailable(registrationCreationDTO);

        LoginSuccessDTO loginSuccessDTO = loginModelDTOMapper.fromExistingRegistration(existingRegistration, userListings);
        return ResponseEntity.accepted().body(loginSuccessDTO);
    }

    @PostMapping(name = "login", value = "/logout", produces = "application/json")
    public ResponseEntity<List<RegistrationCreationDTO>> getAllRegistrations() {
        List<RegistrationCreationDTO> registrationCreationDTOS = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrationCreationDTOS);
    }

}
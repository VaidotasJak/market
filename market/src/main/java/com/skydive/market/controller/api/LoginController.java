package com.skydive.market.controller.api;

import com.skydive.market.controller.request.LoginRequest;
import com.skydive.market.controller.request.RegistrationRequest;
import com.skydive.market.dto.LoginSuccessDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.LoginModelDTOMapper;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import com.skydive.market.service.LoginService;
import com.skydive.market.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final LoginModelDTOMapper loginModelDTOMapper;

    @PostMapping(name = "login", value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginSuccessDTO> login(@RequestBody final LoginRequest loginRequest) {
        Registration existingRegistration = loginService.login(loginModelDTOMapper.mapToModel(loginRequest));

//        List<Listing> listings = listingService.getAllListings();
        LoginSuccessDTO loginSuccessDTO = loginModelDTOMapper.fromExistingRegistration(existingRegistration);

        return ResponseEntity.accepted().body(loginSuccessDTO);
    }

//    @GetMapping("/registrations")
//    public ResponseEntity<List<RegistrationCreationDTO>> getAllRegistrations() {
//        List<RegistrationCreationDTO> registrationCreationDTOS = registrationService.getAllRegistrations();
//        return ResponseEntity.ok(registrationCreationDTOS);
//    }
}
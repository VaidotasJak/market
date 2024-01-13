package com.skydive.market.controller.api;

import com.skydive.market.controller.request.RegistrationRequest;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.model.Registration;
import com.skydive.market.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping(name = "CreateNewUser", value = "/new-registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationCreationDTO> createNewRegistration(@RequestBody RegistrationRequest registrationRequest) {
        Registration newRegistration = registrationService.registerNewUser(new RegistrationModelDTOMapper().mapToModel(registrationRequest));
        RegistrationCreationDTO registrationCreationDTO = new RegistrationModelDTOMapper().fromRegistration(newRegistration);
        return ResponseEntity.accepted().body(registrationCreationDTO);
    }

    @GetMapping("/registrations")
    public ResponseEntity<List<RegistrationCreationDTO>> getAllRegistrations() {
        List<RegistrationCreationDTO> registrationCreationDTOS = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrationCreationDTOS);
    }
}
package com.skydive.market.controller.api;

import com.skydive.market.controller.request.RegistrationRequest;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.RegistrationModelDTOMapper;
import com.skydive.market.model.Registration;
import com.skydive.market.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final RegistrationModelDTOMapper registrationModelDTOMapper;

    @PostMapping(name = "CreateNewUser", value = "/new-registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RegistrationCreationDTO> createNewRegistration(@RequestBody final RegistrationRequest registrationRequest) {
        Registration newRegistration = registrationService.registerNewUser(registrationModelDTOMapper.mapToModel(registrationRequest));
        RegistrationCreationDTO registrationCreationDTO = registrationModelDTOMapper.fromRegistration(newRegistration);
        return ResponseEntity.accepted().body(registrationCreationDTO);
    }

    @GetMapping("/registrations")
    public ResponseEntity<List<RegistrationCreationDTO>> getAllRegistrations() {
        List<RegistrationCreationDTO> registrationCreationDTOS = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrationCreationDTOS);
    }
}
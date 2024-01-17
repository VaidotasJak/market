package com.skydive.market.controller.api;

import com.skydive.market.controller.request.ListingRequest;
import com.skydive.market.dto.ListingCreationDto;
import com.skydive.market.dto.mapper.ListingModelDTOMapper;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import com.skydive.market.service.ListingService;
import com.skydive.market.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;
    private final RegistrationService registrationService;
    private final ListingModelDTOMapper listingModelDTOMapper;
//
    @PostMapping(name = "CreateNewListing", value = "/new-listing/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ListingCreationDto> createNewListing(@RequestBody final ListingRequest listingRequest, @PathVariable final Integer userId) {
        Registration registration = registrationService.getRegistration(userId);
        Listing newListing = listingService.createListing(listingModelDTOMapper.mapToModel(listingRequest), registration);
        ListingCreationDto listingCreationDto = listingModelDTOMapper.fromListing(newListing);
        return new ResponseEntity<>(listingCreationDto, HttpStatus.CREATED);
    }

}
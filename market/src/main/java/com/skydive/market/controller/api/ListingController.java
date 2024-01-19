package com.skydive.market.controller.api;

import com.skydive.market.controller.request.ListingRequest;
import com.skydive.market.dto.ListingCreationDto;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.ListingModelDTOMapper;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import com.skydive.market.service.ListingServiceImpl;
import com.skydive.market.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/listings")
public class ListingController {

    private final ListingServiceImpl listingServiceImpl;
    private final RegistrationService registrationService;
    private final ListingModelDTOMapper listingModelDTOMapper;
//
    @PostMapping(name = "CreateNewListing", value = "/new/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ListingCreationDto> createNewListing(@RequestBody final ListingRequest listingRequest, @PathVariable final Integer userId) {
        Registration registration = registrationService.getRegistration(userId);
        Listing newListing = listingServiceImpl.create(listingModelDTOMapper.mapToModel(listingRequest), registration);
        ListingCreationDto listingCreationDto = listingModelDTOMapper.fromListing(newListing);
        return new ResponseEntity<>(listingCreationDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListingDto>> getAllListings() {
        List<ListingDto> listingDtos = listingServiceImpl.getAllAvailable();
        return ResponseEntity.ok(listingDtos);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<ListingDto>> getAllListings(@PathVariable final Long userId) {
        RegistrationCreationDTO registrationCreationDTO = new RegistrationCreationDTO();
        registrationCreationDTO.setId(userId);
        List<ListingDto> listingDtos = listingServiceImpl.getAllAvailable(registrationCreationDTO);
        return ResponseEntity.ok(listingDtos);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteListing(@PathVariable("id") Long listingId, @RequestHeader("userId") final Integer userId){
        Registration registration = registrationService.getRegistration(userId);
        listingServiceImpl.delete(listingId, registration);
        return ResponseEntity.ok("Listing was deleted successfully!.");
    }
    /**
     * 2. funcionalitu to update listing - so that user could change listingstatus from AVAILABLE to SOLD.
    **/

}
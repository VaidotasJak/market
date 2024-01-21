package com.skydive.market.controller.api;

import com.skydive.market.controller.request.ListingRequest;
import com.skydive.market.dto.ListingAllDto;
import com.skydive.market.dto.ListingCreationDto;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.dto.mapper.ListingModelDTOMapper;
import com.skydive.market.model.Listing;
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

    @PostMapping(name = "CreateNewListing", value = "/new/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ListingCreationDto> createNewListing(@RequestBody final ListingRequest listingRequest, @PathVariable final Integer userId) {
        Registration registration = registrationService.getRegistration(userId);
        Listing newListing = listingServiceImpl.create(listingModelDTOMapper.mapToModel(listingRequest), registration);
        ListingCreationDto listingCreationDto = listingModelDTOMapper.fromListing(newListing);
        return new ResponseEntity<>(listingCreationDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListingAllDto>> getAllListings() {
        List<ListingAllDto> listingAllDtos = listingServiceImpl.generateListing(listingServiceImpl.getAllAvailable());
        return ResponseEntity.ok(listingAllDtos);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<ListingAllDto>> getAllListings(@PathVariable final Long userId) {
        RegistrationCreationDTO registrationDto = new RegistrationCreationDTO();
        registrationDto.setId(userId);
        List<ListingAllDto> listingAllDtos = listingServiceImpl.generateListing(listingServiceImpl.getAllAvailable(registrationDto));
        return ResponseEntity.ok(listingAllDtos);
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
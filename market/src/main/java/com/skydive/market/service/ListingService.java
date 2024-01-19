package com.skydive.market.service;

import com.skydive.market.dto.ListingModelDTO;
import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.model.Registration;
import java.util.List;

public interface ListingService {
    List<ListingDto> getAllAvailable(RegistrationCreationDTO dto);

    List<ListingDto> getAllAvailable();

    Listing create(ListingModelDTO listingModelDTO, Registration registration);

    void delete(Long id, Registration registration);
}

package com.skydive.market.repository;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import com.skydive.market.service.hibernateService.HibernateService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ListingRepository {

    public Listing save(Listing listing) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(listing);
        transaction.commit();
        session.close();
        log.info("New listing was created");
        return listing;
    }

    public List<Listing> fetchAllListings(RegistrationCreationDTO dto) {
        List<Listing> listings = new ArrayList<>();
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM listing WHERE registration_id = " + dto.getId() + ";", Listing.class);
        @SuppressWarnings("unchecked")
        List<Listing> items = (List<Listing>) query.getResultList();
        listings = items;
        transaction.commit();
        session.close();
        return listings;
    }
}

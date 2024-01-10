package com.skydive.market.repository;

import com.skydive.market.model.ListingStatus;
import com.skydive.market.model.listing.Listing;
import com.skydive.market.model.registration.Registration;
import com.skydive.market.service.hibernateService.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RegistrationRepository {
    public void saveAll(List<Registration> registrations) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (Registration registration : registrations) {
            for (Listing listing : registration.getListings()) {
                listing.setListingStatus(ListingStatus.AVAILABLE);
                listing.setRegistration(registration);
                session.save(listing);
            }
            session.save(registration);
        }
        transaction.commit();
        session.close();
        System.out.println("DATA IS PUBLISHED");
    }
}

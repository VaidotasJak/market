package com.skydive.market.repository;

import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.model.enums.ListingStatus;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import com.skydive.market.service.hibernateService.HibernateService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class RegistrationRepository {
    public void saveAll(List<Registration> registrations) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        registrations.forEach(registration -> {
            registration.getListings().forEach(listing -> {
                listing.setListingStatus(ListingStatus.AVAILABLE);
                listing.setRegistration(registration);
                session.save(listing);
            });
            session.save(registration);
        });
        transaction.commit();
        session.close();
        log.info("DATA IS PUBLISHED");
    }

    public Registration save(Registration registration) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(registration);
        transaction.commit();
        session.close();
        log.info("NEW USER WAS CREATED");
        return registration;
    }

    public List<Registration> fetchData(final RegistrationModelDTO dto) {
        List<Registration> registrations;
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT *\n" +
                "FROM REGISTRATION\n" +
                "WHERE EMAIL = '" + dto.getEmail() + "' OR PHONENUMBER = '" + dto.getPhoneNumber() + "';", Registration.class);
        @SuppressWarnings("unchecked")
        List<Registration> items = (List<Registration>) query.getResultList();
        registrations = items;
        transaction.commit();
        session.close();
        return registrations;
    }

    public List<Registration> getAllRegistrations() {
        List<Registration> registrations;
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM REGISTRATION;", Registration.class);
        @SuppressWarnings("unchecked")
        List<Registration> items = (List<Registration>) query.getResultList();
        registrations = items;
        transaction.commit();
        session.close();
        return registrations;
    }
}

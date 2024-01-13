package com.skydive.market.repository;

import com.skydive.market.model.enums.ListingStatus;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import com.skydive.market.service.hibernateService.HibernateService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
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

    public Registration save(Registration registration) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(registration);
        transaction.commit();
        session.close();
        System.out.println("NEW USER WAS CREATED");
        return registration;
    }

    public List<Registration> fetchData(String email, Long phoneNumber) {
        List<Registration> registrations;
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT *\n" +
                "FROM REGISTRATION\n" +
                "WHERE EMAIL = '" + email + "' OR PHONENUMBER = '" + phoneNumber + "';", Registration.class);
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

package com.skydive.market.repository;

import com.skydive.market.dto.LoginModelDTO;
import com.skydive.market.dto.RegistrationModelDTO;
import com.skydive.market.model.Registration;
import com.skydive.market.model.enums.ListingStatus;
import com.skydive.market.service.hibernateService.HibernateService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class LoginRepository {

    public List<Registration> fetchDataByEmail(final LoginModelDTO dto) {
        List<Registration> registrations;
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

//        Query query = session.createNativeQuery("SELECT * FROM REGISTRATION WHERE EMAIL = '" + dto.getEmail() + "';", Registration.class);
        Query query = session.createNativeQuery("SELECT * FROM REGISTRATION;", Registration.class);
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

package com.skydive.market.repository;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.service.hibernateService.HibernateService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<ListingDto> fetch(Long id) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT id, description, listingstatus, name, price, weight FROM listing WHERE id = " + id + ";");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> resultList = query.list();

        @SuppressWarnings("unchecked")
        List<ListingDto> items = (List<ListingDto>) query.getResultList();
        transaction.commit();
        session.close();
        return items;
    }

    public void delete(Listing listing) {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(listing);
        transaction.commit();
        session.close();
        log.info("Listing was deleted");
    }

    public List<ListingDto> fetchAllAvailableListings(RegistrationCreationDTO dto) {
        List<ListingDto> listings = new ArrayList<>();
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT id, description, listingstatus, name, price, weight FROM listing WHERE registration_id = " + dto.getId() +
                " and listingstatus = 1;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> resultList = query.list();

        @SuppressWarnings("unchecked")
        List<ListingDto> items = (List<ListingDto>) query.getResultList();
        listings = items;
        transaction.commit();
        session.close();
        return listings;
    }

    public List<ListingDto> fetchAllAvailableListings() {
        List<ListingDto> listings = new ArrayList<>();
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createNativeQuery("SELECT id, description, listingstatus, name, price, weight FROM listing WHERE listingstatus = 1;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> resultList = query.list();

        @SuppressWarnings("unchecked")
        List<ListingDto> items = (List<ListingDto>) query.getResultList();
        listings = items;
        transaction.commit();
        session.close();
        return listings;
    }

}

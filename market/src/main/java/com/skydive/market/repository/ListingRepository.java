package com.skydive.market.repository;

import com.skydive.market.dto.RegistrationCreationDTO;
import com.skydive.market.model.Listing;
import com.skydive.market.model.ListingDto;
import com.skydive.market.service.hibernateService.HibernateService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

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

        SQLQuery query = session.createSQLQuery("SELECT id, description, listingstatus, name, price, weight FROM listing WHERE id = " + id + ";");
        query.setResultTransformer(Transformers.aliasToBean(ListingDto.class));
        List<ListingDto> resultList = query.list();
        transaction.commit();
        session.close();
        return resultList;
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
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String nativeQuery = "SELECT id, description, listingstatus, name, price, weight FROM listing WHERE registration_id = " + dto.getId() +
                " and listingstatus = 1;";
        SQLQuery query = session.createSQLQuery(nativeQuery);
        query.setResultTransformer(Transformers.aliasToBean(ListingDto.class));
        List<ListingDto> resultList = query.list();
        transaction.commit();
        session.close();
        return resultList;
    }

    public List<ListingDto> fetchAllAvailableListings() {
        Session session = HibernateService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String nativeQuery = "SELECT id, description, listingStatus, name, price, weight FROM listing WHERE listingStatus = 1;";
        SQLQuery query = session.createSQLQuery(nativeQuery);
        query.setResultTransformer(Transformers.aliasToBean(ListingDto.class));
        List<ListingDto> resultList = query.list();
        transaction.commit();
        session.close();
        return resultList;
    }

}

package com.skydive.market.service.hibernateService;
import com.skydive.market.model.Listing;
import com.skydive.market.model.Registration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateService {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.h2.Driver");
            properties.put(Environment.URL, "jdbc:h2:tcp://localhost/~/test");
            properties.put(Environment.USER, "sa");
            properties.put(Environment.PASS, "");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(Registration.class);
            configuration.addAnnotatedClass(Listing.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }
}
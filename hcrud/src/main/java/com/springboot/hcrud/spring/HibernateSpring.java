package com.springboot.hcrud.spring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateSpring implements HibernateService {

    private final MetadataSources metadataSources;

    public HibernateSpring(MetadataSources metadataSources) {
        this.metadataSources = metadataSources;
    }

    @Override
    public <T> void hibernateInsertEntities(List<T> listItems, Class<T> entity) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entity).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            listItems.forEach(session::save);

        } finally {
            tr.commit();
            session.close();
        }
    }

    @Override
    public <T> void hibernateInsertEntity(T item, Class<T> entity) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entity).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            session.save(item);

        } finally {
            tr.commit();
            session.close();
        }
    }

    @Override
    public <T> List<T> hibernateRetrieveAllData(Class<T> entityClass) {
        metadataSources.addAnnotatedClass(entityClass);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        Transaction tr = session.beginTransaction();
        List<T> entitiesList;

        try {
            Query<T> query = session.createQuery("select e from " + entityClass.getSimpleName() + " e",
                    entityClass);
            entitiesList = query.list();
        } finally {
            tr.commit();
            session.close();
        }
        return entitiesList;
    }

    private <T> Metadata getMetadata(Class<T> entity) {
        metadataSources.addAnnotatedClass(entity);
        return metadataSources.buildMetadata();
    }
}

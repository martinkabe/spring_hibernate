package com.springframework.spring5recipeapp.service;

import com.springframework.spring5recipeapp.data.Employee;
import com.springframework.spring5recipeapp.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class EmployeeQueryService implements QueryService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MetadataSources getMetaSource;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<Employee> JPQLQuery() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        TypedQuery<Employee> query = em.createQuery("Select e from Employee e ", Employee.class);
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }

    @Override
    public List<Employee> studentAllData() {
        return employeeRepository.findAll();
    }

    @Override
    public <T> List<T> hibernateAllData(Class<T> entityClass) {
        getMetaSource.addAnnotatedClass(entityClass);
        Metadata metadata = getMetaSource.buildMetadata();

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
}
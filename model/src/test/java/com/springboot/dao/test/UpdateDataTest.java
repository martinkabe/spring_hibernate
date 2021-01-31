package com.springboot.dao.test;

import com.springboot.dao.data.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "uat", "uat-test" })
public class UpdateDataTest {

    @Autowired
    private MetadataSources metadataSources;

    @Test
    public void update_EmpoloyeeRecordBasedOnEmail() {
        metadataSources.addAnnotatedClass(Employee.class);
        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        try {
            session.createQuery("update Employee e set email = 'email@updated.com' where e.email = 'ei@google.com'")
                    .executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

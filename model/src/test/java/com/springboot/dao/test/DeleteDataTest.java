package com.springboot.dao.test;

import com.springboot.dao.data.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "uat", "uat-test" })
public class DeleteDataTest {

    private final Logger log = LoggerFactory.getLogger(DeleteDataTest.class);

    @Autowired
    private MetadataSources metadataSources;

    @Test
    public void delete_EmployeeRecordBasedOnEmail_QueryApproach() {
        metadataSources.addAnnotatedClass(Employee.class);
        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        try {
            session.createQuery("delete from Employee e where e.email = 'email@updated.com'")
                    .executeUpdate();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Test
    public void delete_EmployeeRecordBasedOnEmail_DeleteApproach() {
        metadataSources.addAnnotatedClass(Employee.class);
        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        try {
            // get employee based on id
            Employee myEmployee = session.get(Employee.class, 112);
            log.info("Employee: {}/{}", myEmployee.getId(), myEmployee.getEmail());

            // delete the employee
            log.info("Deleting employee ...");
            session.delete(myEmployee);

            // commit the transaction
            log.info("Commiting the transaction ...");
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
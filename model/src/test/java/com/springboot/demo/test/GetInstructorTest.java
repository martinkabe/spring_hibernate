package com.springboot.demo.test;

import com.springboot.dao.test.DeleteDataTest;
import com.springboot.demo.Instructor;
import com.springboot.demo.InstructorDetail;
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
public class GetInstructorTest {

    private final Logger log = LoggerFactory.getLogger(DeleteDataTest.class);

    @Autowired
    private MetadataSources metadataSources;

    @Test
    public void create_InstructorRecord() {
        metadataSources.addAnnotatedClass(Instructor.class);
        metadataSources.addAnnotatedClass(InstructorDetail.class);
        Metadata metadata = metadataSources.buildMetadata();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, 2);
            log.info("instructor detail: {}", tempInstructorDetail);
            log.info("instructor: {}", tempInstructorDetail.getInstructor());

            session.getTransaction().commit();
        } catch (Exception ex) {
            log.warn("Exception: {}", ex.getMessage());
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}

package com.springboot.demo.test;

import com.springboot.demo.Instructor;
import com.springboot.demo.InstructorDetail;
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
public class CreateInstructorTest {

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

            Instructor tempInstructor = new Instructor("Johny", "Walker", "jwalker@gmail.com");

            InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.somechannel.com", "football");

            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.save(tempInstructor);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

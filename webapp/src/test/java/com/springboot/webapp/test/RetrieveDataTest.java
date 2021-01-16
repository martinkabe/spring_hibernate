package com.springboot.webapp.test;

import com.springboot.hcrud.data.Employee;
import com.springboot.hcrud.repository.DataQueries;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles({ "uat", "uat-test" })
@ComponentScan("com.springboot.hcrud")
public class RetrieveDataTest {

    private final Logger log = LoggerFactory.getLogger(RetrieveDataTest.class);

    @Autowired
    private DataQueries dataQueries;

    @Test
    public void retrieveAllEmployeeData_HibernateApproach() {
        List<Employee> employees = dataQueries.getAllEntitiesHibernate(Employee.class);
        log.info("#Employees: {}", employees.size());
    }
}
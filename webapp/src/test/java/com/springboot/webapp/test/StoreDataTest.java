package com.springboot.webapp.test;

import com.springboot.dao.data.Employee;
import com.springboot.dao.repository.DataQueries;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({ "uat", "uat-test" })
public class StoreDataTest {

    private final Logger log = LoggerFactory.getLogger(RetrieveDataTest.class);

    @Autowired
    private DataQueries dataQueries;

    @Test
    public void saveData_Hibernate_SingleEmployee() {
        Employee emp = new Employee("Justin", "Gilmore", "jgilmore@gmail.com");
        dataQueries.insertEntityHibernate(emp, Employee.class);
    }
}

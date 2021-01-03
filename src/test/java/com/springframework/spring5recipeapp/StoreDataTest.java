package com.springframework.spring5recipeapp;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.springframework.spring5recipeapp.data.Employee;
import com.springframework.spring5recipeapp.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class StoreDataTest {

    private final Logger log = LoggerFactory.getLogger(RetrieveDataTest.class);

    private Connection myConn = null;

    @Autowired
    private Connection getProperties;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    public void before() throws SQLException {
        log.info("Connecting to database: {}", ((ConnectionImpl) getProperties).getDatabase());
        myConn = getProperties;
    }

    @AfterEach
    public void after() throws SQLException {
        myConn.close();
        assertTrue(myConn.isClosed());
    }

    @Test
    public void saveData_CrudRepository() {
        Employee employee = new Employee("Ivan", "Hrozny", "ihrozny@gmail.com");
        employee = employeeRepository.save(employee);
        log.info("Employee added: {}", employee);
    }
}

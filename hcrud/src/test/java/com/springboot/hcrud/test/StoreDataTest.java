package com.springboot.hcrud.test;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.springboot.hcrud.data.Employee;
import com.springboot.hcrud.repository.EmployeeCrudRepository;
import com.springboot.hcrud.repository.EmployeeQueries;
import com.springboot.hcrud.spring.HibernateService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@ActiveProfiles({ "uat", "uat-test" })
public class StoreDataTest {

    private final Logger log = LoggerFactory.getLogger(RetrieveDataTest.class);

    private Connection myConn = null;

    @Autowired
    private Connection getProperties;

    @Autowired
    private EmployeeCrudRepository employeeCrudRepository;

    @Autowired
    private EmployeeQueries queries;

    @Autowired
    private HibernateService hService;

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
        employee = employeeCrudRepository.save(employee);
        log.info("Employee added: {}", employee);
    }

    @Test
    public void saveData_JdbcTemplate_OneEmployee() {
        Employee employee = new Employee("Chris", "Malvin", "cmalvin@gmail.com");
        boolean isInserted = queries.insertEmloyee(employee);
        assertTrue(isInserted);
    }

    @Test
    public void saveData_JdbcTemplate_MultipleEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Melwin", "Dirkman", "mdirkman@gmail.com"));
        employees.add(new Employee("Kirk", "Douglas", "kdouglas@gmail.com"));
        employees.add(new Employee("Nathan", "Calm", "ncalm@gmail.com"));

        queries.insertEmployees(employees);
    }

    @Test
    public void saveData_Hibernate_MultipleEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Kirk", "McLeod", "kmcleod@gmail.com"));
        employees.add(new Employee("Dirk", "McLeod", "dmcleod@gmail.com"));
        hService.hibernateInsertEntities(employees, Employee.class);
    }

    @Test
    public void saveData_Hibernate_SingleEmployee() {
        Employee emp = new Employee("Justin", "Gilmore", "jgilmore@gmail.com");
        hService.hibernateInsertEntity(emp, Employee.class);
    }
}

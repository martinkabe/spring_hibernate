package com.springframework.spring5recipeapp;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.springframework.spring5recipeapp.data.Employee;
import com.springframework.spring5recipeapp.repository.EmployeeQueries;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TestJdbc {

    private final Logger log = LoggerFactory.getLogger(TestJdbc.class);

    private Connection myConn = null;
    private Statement stm = null;

    @Autowired
    private Connection getProperties;

    @Autowired
    EmployeeQueries queries;

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
    public void testJdbc() {
        try {
            log.info("Connecting successful, host: {}", myConn.getCatalog());
            stm = myConn.createStatement();
            String sql = "SELECT id, first_name, last_name FROM employee";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                log.info("id = {}, first_name = {}, last_name = {}",
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testHibernate() {
        List<Employee> employees = queries.getEmployees();
        log.info("Count of employees: {}", employees.size());
    }
}
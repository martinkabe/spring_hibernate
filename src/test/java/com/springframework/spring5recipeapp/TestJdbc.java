package com.springframework.spring5recipeapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

@SpringBootTest
public class TestJdbc {

    private final Logger log = LoggerFactory.getLogger(TestJdbc.class);

    private Connection myConn = null;

    @BeforeEach
    public void before() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
        String userName = "hbstudent";
        String password = "hbstudent";
        log.info("Connecting to database: {}", jdbcUrl);
        myConn = DriverManager.getConnection(jdbcUrl, userName, password);
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

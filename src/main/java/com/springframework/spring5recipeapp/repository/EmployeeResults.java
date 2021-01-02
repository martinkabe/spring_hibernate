package com.springframework.spring5recipeapp.repository;

import com.springframework.spring5recipeapp.data.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeResults implements EmployeeQueries {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeResults(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        String sql = "SELECT * FROM employee";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Employee(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email")
                        )
        );
    }
}

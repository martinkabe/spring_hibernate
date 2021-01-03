package com.springframework.spring5recipeapp.repository;

import com.springframework.spring5recipeapp.data.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeResults implements EmployeeQueries {

    private static final String RETRIEVE_EMPLOYEES = "SELECT * FROM employee";
    private static final String INSERT_EMPLOYEES = "INSERT INTO employee(first_name, last_name, email) VALUES(?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    public EmployeeResults(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getEmployees() {
        return jdbcTemplate.query(
                RETRIEVE_EMPLOYEES,
                (rs, rowNum) ->
                        new Employee(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email")
                        )
        );
    }

    @Override
    public boolean insertEmloyee(Employee emp) {
        int insert = jdbcTemplate.update(INSERT_EMPLOYEES, emp.getFirstName(), emp.getLastName(), emp.getEmail());
        return insert > 0;
    }

    @Override
    public int[][] insertEmployees(List<Employee> employees) {
        return jdbcTemplate.batchUpdate(
                INSERT_EMPLOYEES,
                employees,
                1000,
                (ps, emp) -> {
                    ps.setString(1, emp.getFirstName());
                    ps.setString(2, emp.getLastName());
                    ps.setString(3, emp.getEmail());
                });
    }
}

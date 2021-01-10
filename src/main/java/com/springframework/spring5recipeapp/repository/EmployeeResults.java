package com.springframework.spring5recipeapp.repository;

import com.springframework.spring5recipeapp.data.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeResults implements EmployeeQueries {

    private static final String RETRIEVE_EMPLOYEES = "SELECT * FROM employee";
    private static final String INSERT_EMPLOYEES = "INSERT INTO employee(first_name, last_name, email) VALUES(?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private MetadataSources getMetaSource;

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
    public void insertEmployees(List<Employee> employees) {
        jdbcTemplate.batchUpdate(
                INSERT_EMPLOYEES,
                employees,
                1000,
                (ps, emp) -> {
                    ps.setString(1, emp.getFirstName());
                    ps.setString(2, emp.getLastName());
                    ps.setString(3, emp.getEmail());
                });
    }

    @Override
    public void hibernateInsertEmployees(List<Employee> employees) {
        getMetaSource.addAnnotatedClass(Employee.class);
        Metadata metadata = getMetaSource.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            for (Employee employee : employees)
                session.save(employee);

        } finally {
            tr.commit();
            session.close();
        }
    }
}

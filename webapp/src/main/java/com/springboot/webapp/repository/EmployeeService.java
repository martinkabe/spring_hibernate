package com.springboot.webapp.repository;

import com.springboot.webapp.data.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeService implements EmployeeQueries {

    private static final String RETRIEVE_EMPLOYEES = "SELECT * FROM employee";
    private static final String INSERT_EMPLOYEES = "INSERT INTO employee(first_name, last_name, email) VALUES(?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private MetadataSources getMetaSource;

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    EmployeeCrudRepository employeeCrudRepository;

    public EmployeeService(JdbcTemplate jdbcTemplate) {
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
    public <T> void hibernateInsertEntities(List<T> listItems, Class<T> entity) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entity).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            listItems.forEach(session::save);

        } finally {
            tr.commit();
            session.close();
        }
    }

    @Override
    public <T> void hibernateInsertEntity(T item, Class<T> entity) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entity).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            session.save(item);

        } finally {
            tr.commit();
            session.close();
        }
    }

    @Override
    public List<Employee> JPQLQuery() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        TypedQuery<Employee> query = em.createQuery("Select e from Employee e ", Employee.class);
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }

    @Override
    public List<Employee> studentAllData() {
        return employeeCrudRepository.findAll();
    }

    @Override
    public <T> List<T> hibernateAllData(Class<T> entityClass) {
        getMetaSource.addAnnotatedClass(entityClass);
        Metadata metadata = getMetaSource.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        Transaction tr = session.beginTransaction();
        List<T> entitiesList;

        try {
            Query<T> query = session.createQuery("select e from " + entityClass.getSimpleName() + " e",
                    entityClass);
            entitiesList = query.list();
        } finally {
            tr.commit();
            session.close();
        }
        return entitiesList;
    }

    private <T> Metadata getMetadata(Class<T> entity) {
        getMetaSource.addAnnotatedClass(entity);
        return getMetaSource.buildMetadata();
    }
}

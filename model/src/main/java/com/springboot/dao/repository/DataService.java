package com.springboot.dao.repository;

import com.springboot.dao.data.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class DataService implements DataQueries {

    private static final String RETRIEVE_EMPLOYEES = "SELECT * FROM employee";
    private static final String INSERT_EMPLOYEES = "INSERT INTO employee(first_name, last_name, email) VALUES(?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;
    private final MetadataSources metadataSources;
    private final EntityManagerFactory emf;
    private final EmployeeJpaRepository employeeJpaRepository;

    public DataService(JdbcTemplate jdbcTemplate,
                       MetadataSources metadataSources,
                       EntityManagerFactory emf, EmployeeJpaRepository employeeJpaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.metadataSources = metadataSources;
        this.emf = emf;
        this.employeeJpaRepository = employeeJpaRepository;
    }

    @Override
    public List<Employee> getEmployeesJdbcTemplate() {
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
    public boolean insertEmloyeeJdbcTemplateUpdate(Employee emp) {
        int insert = jdbcTemplate.update(INSERT_EMPLOYEES, emp.getFirstName(), emp.getLastName(), emp.getEmail());
        return insert > 0;
    }

    @Override
    public void insertEmloyeeJdbcTemplateBatchUpdate(List<Employee> employees) {
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
    public List<Employee> getEmployeesEntityManager() {
        EntityManager em = emf.createEntityManager();
        //em.getTransaction().begin( );

        TypedQuery<Employee> query = em.createQuery("Select e from Employee e ", Employee.class);
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }

    @Override
    public List<Employee> getEmployeesJpa() {
        return employeeJpaRepository.findAll();
    }

    @Override
    public <T> void insertEntitiesHibernate(List<T> listItems, Class<T> entity) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entity).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        Transaction tr = session.beginTransaction();

        try {
            listItems.forEach(session::save);
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public <T> void insertEntityHibernate(T item, Class<T> entity) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entity).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        try {
            session.save(item);
            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public <T> List<T> getAllEntitiesHibernate(Class<T> entityClass) {
        // here we build the SessionFactory (Hibernate 5.4.27.Final)
        SessionFactory sessionFactory = getMetadata(entityClass).getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        List<T> entitiesList;

        try {
            Query<T> query = session.createQuery("from " + entityClass.getSimpleName(),
                    entityClass);
            entitiesList = query.getResultList();
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
        return entitiesList;
    }

    private <T> Metadata getMetadata(Class<T> entity) {
        metadataSources.addAnnotatedClass(entity);
        return metadataSources.buildMetadata();
    }
}

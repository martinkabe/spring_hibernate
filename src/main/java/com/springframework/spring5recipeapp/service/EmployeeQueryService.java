package com.springframework.spring5recipeapp.service;

import com.springframework.spring5recipeapp.data.Employee;
import com.springframework.spring5recipeapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class EmployeeQueryService implements QueryService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EntityManagerFactory emf;

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
        return employeeRepository.findAll();
    }
}

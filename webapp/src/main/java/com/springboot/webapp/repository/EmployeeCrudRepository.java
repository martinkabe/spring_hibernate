package com.springboot.webapp.repository;

import com.springboot.webapp.data.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
    @Override
    List<Employee> findAll();
}

package com.springboot.hcrud.repository;

import com.springboot.hcrud.data.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
    @Override
    List<Employee> findAll();
}

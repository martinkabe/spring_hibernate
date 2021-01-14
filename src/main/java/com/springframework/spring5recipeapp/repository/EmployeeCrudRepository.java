package com.springframework.spring5recipeapp.repository;

import com.springframework.spring5recipeapp.data.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCrudRepository extends CrudRepository<Employee, Integer> {
    @Override
    List<Employee> findAll();
}

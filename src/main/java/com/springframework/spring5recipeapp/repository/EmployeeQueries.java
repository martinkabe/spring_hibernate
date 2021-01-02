package com.springframework.spring5recipeapp.repository;

import com.springframework.spring5recipeapp.data.Employee;

import java.util.List;

public interface EmployeeQueries {
    List<Employee> getEmployees();
}

package com.springframework.spring5recipeapp.repository;

import com.springframework.spring5recipeapp.data.Employee;

import java.util.List;

public interface EmployeeQueries {
    List<Employee> getEmployees();
    boolean insertEmloyee(Employee emp);
    void insertEmployees(List<Employee> employees);
    <T> void hibernateInsertEntities(List<T> listItems, Class<T> entity);
    <T> void hibernateInsertEntity(T item, Class<T> entity);
}

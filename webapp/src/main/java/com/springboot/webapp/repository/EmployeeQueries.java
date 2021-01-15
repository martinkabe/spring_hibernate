package com.springboot.webapp.repository;

import com.springboot.webapp.data.Employee;

import java.util.List;

public interface EmployeeQueries {
    List<Employee> getEmployees();
    boolean insertEmloyee(Employee emp);
    void insertEmployees(List<Employee> employees);
    <T> void hibernateInsertEntities(List<T> listItems, Class<T> entity);
    <T> void hibernateInsertEntity(T item, Class<T> entity);
    List<Employee> JPQLQuery();
    List<Employee> studentAllData();
    <T> List<T> hibernateAllData(Class<T> entityClass);
}

package com.springboot.hcrud.repository;

import com.springboot.hcrud.data.Employee;

import java.util.List;

public interface EmployeeQueries {
    List<Employee> getEmployees();
    boolean insertEmloyee(Employee emp);
    void insertEmployees(List<Employee> employees);
    List<Employee> JPQLQuery();
    List<Employee> studentAllData();
}

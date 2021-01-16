package com.springboot.hcrud.repository;

import com.springboot.hcrud.data.Employee;

import java.util.List;

public interface DataQueries {
    List<Employee> getEmployeesJdbcTemplate();
    boolean insertEmloyeeJdbcTemplateUpdate(Employee emp);
    void insertEmloyeeJdbcTemplateBatchUpdate(List<Employee> employees);
    List<Employee> getEmployeesEntityManager();
    <T> void insertEntitiesHibernate(List<T> listItems, Class<T> entity);
    <T> void insertEntityHibernate(T item, Class<T> entity);
    <T> List<T> getAllEntitiesHibernate(Class<T> entityClass);
}
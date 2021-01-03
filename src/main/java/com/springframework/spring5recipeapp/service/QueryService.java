package com.springframework.spring5recipeapp.service;

import com.springframework.spring5recipeapp.data.Employee;

import java.util.List;

public interface QueryService {
    List<Employee> JPQLQuery();
    List<Employee> studentAllData();
}

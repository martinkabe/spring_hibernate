package com.springboot.dao.repository;

import com.springboot.dao.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
}

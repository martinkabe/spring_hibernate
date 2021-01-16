package com.springboot.hcrud.repository;

import com.springboot.hcrud.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
}

package com.ironhack.lab42.repository;

import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByStatus (Status status);
    List<Employee> findByDepartment (String department);
}

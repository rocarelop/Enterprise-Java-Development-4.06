package com.ironhack.lab42.controller.interfaces;


import com.ironhack.lab42.controller.DTO.EmployeeDepartmentDTO;
import com.ironhack.lab42.controller.DTO.EmployeeStatusDTO;
import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.model.Patient;

import java.util.List;
import java.util.Optional;

public interface EmployeeController {

    List<Employee> findAll();
    List<Employee> findByStatus();
    List<Employee> findByDepartment (String department);

    Employee store(Employee employee);
    void updateStatus(int id, EmployeeStatusDTO employeeStatusDTO);
    void updateDepartment(int id, EmployeeDepartmentDTO employeeDepartmentDTO);

}

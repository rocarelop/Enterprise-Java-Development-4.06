package com.ironhack.lab42.service.impl;

import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.repository.EmployeeRepository;
import com.ironhack.lab42.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void updateStatus(int id, Status status) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(!optionalEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        optionalEmployee.get().setStatus(status);
        employeeRepository.save(optionalEmployee.get());
    }


    public void updateDepartment(int id, String department) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(!optionalEmployee.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        optionalEmployee.get().setDepartment(department);
        employeeRepository.save(optionalEmployee.get());

    }



}

package com.ironhack.lab42.controller.impl;

import com.ironhack.lab42.controller.DTO.EmployeeDepartmentDTO;
import com.ironhack.lab42.controller.DTO.EmployeeStatusDTO;
import com.ironhack.lab42.controller.interfaces.EmployeeController;
import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.model.Patient;
import com.ironhack.lab42.repository.EmployeeRepository;
import com.ironhack.lab42.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeControllerImpl implements EmployeeController {
@Autowired
private EmployeeRepository employeeRepository;
@Autowired
private EmployeeService employeeService;

@GetMapping("/employees")
@ResponseStatus(HttpStatus.OK)
public List <Employee> findAll(){
    return employeeRepository.findAll();
}

@GetMapping("/employees/{id}")
public Employee findById(@PathVariable(name = "id") int id) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        return optionalEmployee.get();
    }

@GetMapping("/employees/status")
@ResponseStatus(HttpStatus.OK)
public List<Employee> findByStatus() {

    List <Employee> employeeList = employeeRepository.findByStatus(Status.OFF);
    return employeeList;

}
@GetMapping("/employees/employees")
@ResponseStatus(HttpStatus.OK)
public List<Employee> findByDepartment(@RequestParam (name= "department") String department) {
    List <Employee> departmentList = employeeRepository.findByDepartment(department);
        return departmentList;
    }

    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee store(@RequestBody @Valid Employee employee) {
        return employeeRepository.save(employee);
    }


    @PatchMapping("/employees/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable int id, @RequestBody @Valid EmployeeStatusDTO employeeStatusDTO) {
        employeeService.updateStatus(id, employeeStatusDTO.getStatus());
    }


    @PatchMapping("/employees/{id}/department")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartment(@PathVariable int id, @RequestBody @Valid EmployeeDepartmentDTO employeeDepartmentDTO) {
        employeeService.updateDepartment(id, employeeDepartmentDTO.getDepartment());

    }


}



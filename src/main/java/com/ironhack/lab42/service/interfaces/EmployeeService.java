package com.ironhack.lab42.service.interfaces;

import com.ironhack.lab42.enums.Status;

public interface EmployeeService {
    void updateStatus(int id, Status status);
    void updateDepartment(int id, String department);
}

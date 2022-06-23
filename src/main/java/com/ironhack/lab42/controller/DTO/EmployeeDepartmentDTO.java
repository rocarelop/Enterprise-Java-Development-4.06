package com.ironhack.lab42.controller.DTO;

public class EmployeeDepartmentDTO {

    private String department;

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

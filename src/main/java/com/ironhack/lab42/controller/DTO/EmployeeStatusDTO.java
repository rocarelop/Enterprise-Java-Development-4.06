package com.ironhack.lab42.controller.DTO;

import com.ironhack.lab42.enums.Status;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class EmployeeStatusDTO {

    private Status status;

    public EmployeeStatusDTO() {
    }

    public EmployeeStatusDTO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

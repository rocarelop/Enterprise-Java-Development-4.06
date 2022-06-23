package com.ironhack.lab42.controller.DTO;

import com.ironhack.lab42.model.Employee;

import javax.persistence.*;
import java.util.Date;

public class PatientAdmittedByDTO {


    private String name;

    private Date dateOfBirth;

    private int admittedBy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(int admittedBy) {
        this.admittedBy = admittedBy;
    }
}

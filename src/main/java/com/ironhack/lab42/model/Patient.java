package com.ironhack.lab42.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;
    @Column(name = "patient_name")
    private String name;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "admitted_by")
    private Employee employee;


    public Patient() {
    }

    public Patient(String name, Date dateOfBirth, Employee employee) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
package com.ironhack.lab42.controller.interfaces;


import com.ironhack.lab42.controller.DTO.PatientAdmittedByDTO;
import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientController {

    List <Patient> findAll();
    Patient findById(int id);

    List<Patient> findByDateOfBirthBetween(Date firstDate, Date secondDate);

    List<Patient> findByStatusOff();

    List<Patient> findByDepartmentAdmittingDoctor(String department);

    Patient store(PatientAdmittedByDTO patientAdmittedByDTO);

    void renovate(int id, Patient patient);




}

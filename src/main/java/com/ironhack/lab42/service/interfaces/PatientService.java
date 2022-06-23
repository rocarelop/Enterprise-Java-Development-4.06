package com.ironhack.lab42.service.interfaces;

import com.ironhack.lab42.controller.DTO.PatientAdmittedByDTO;
import com.ironhack.lab42.model.Patient;

public interface PatientService {

    void renovate(int id, Patient patient);
    Patient save(PatientAdmittedByDTO patientAdmittedByDTO);

}

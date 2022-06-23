package com.ironhack.lab42.service.impl;

import com.ironhack.lab42.controller.DTO.PatientAdmittedByDTO;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.model.Patient;
import com.ironhack.lab42.repository.EmployeeRepository;
import com.ironhack.lab42.repository.PatientRepository;
import com.ironhack.lab42.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public void renovate(int id, Patient patient) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(!patientOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        patient.setId(id);
        patientRepository.save(patient);

    }


    public Patient save(PatientAdmittedByDTO patientAdmittedByDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(patientAdmittedByDTO.getAdmittedBy());

        if(!employeeOptional.isPresent()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not exist");
        }
        Patient patient =new Patient(patientAdmittedByDTO.getName(), patientAdmittedByDTO.getDateOfBirth(), employeeOptional.get());
        return patient;
    }
}

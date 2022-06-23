package com.ironhack.lab42.controller.impl;

import com.ironhack.lab42.controller.DTO.PatientAdmittedByDTO;
import com.ironhack.lab42.controller.interfaces.PatientController;
import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Employee;
import com.ironhack.lab42.model.Patient;
import com.ironhack.lab42.repository.EmployeeRepository;
import com.ironhack.lab42.repository.PatientRepository;
import com.ironhack.lab42.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
public class PatientControllerImpl implements PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    @GetMapping("/patients/{id}")
    public Patient findById(@PathVariable(name = "id") int id) {

        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.get();
    }

    @GetMapping("/patients/dateBetween")
    public List<Patient> findByDateOfBirthBetween(@RequestParam(name="firstDate") Date firstDate,
                                                  @RequestParam(name = "secondDate") Date secondDate){
        List <Patient> patientList = patientRepository.findByDateOfBirthBetween(firstDate,secondDate);

        return patientList;

    }

    @GetMapping("/patients/departmentAdmitting")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByDepartmentAdmittingDoctor(@RequestParam(name= "department")String department) {
        List <Patient> patientList = patientRepository.findByDepartmentAdmittingDoctor(department);
        return patientList;
    }


    @GetMapping("/patients/statusOff")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByStatusOff() {
        List<Patient> patientList = patientRepository.findByStatusOff(Status.OFF);
        return patientList;
    }

   @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient store(@RequestBody @Valid PatientAdmittedByDTO patientAdmittedByDTO) {
        return patientService.save(patientAdmittedByDTO);
    }


    @PutMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void renovate(@PathVariable int id, @RequestBody @Valid Patient patient) {
        patientService.renovate(id, patient);

    }


}

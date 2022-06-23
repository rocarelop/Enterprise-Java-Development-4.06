package com.ironhack.lab42.repository;

import com.ironhack.lab42.enums.Status;
import com.ironhack.lab42.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByDateOfBirthBetween(Date firstDate, Date secondDate);

    @Query("SELECT p FROM Patient p JOIN FETCH p.employee AS e WHERE e.department = :department")
    List<Patient> findByDepartmentAdmittingDoctor(@Param("department") String department);

    @Query("SELECT p FROM Patient p JOIN FETCH p.employee AS e WHERE e.status =:status")
    List<Patient> findByStatusOff(@Param("status")Status status);
}

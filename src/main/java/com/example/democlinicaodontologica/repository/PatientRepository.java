package com.example.democlinicaodontologica.repository;
import com.example.democlinicaodontologica.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT name, lastname FROM Patient WHERE dni=?1")
    Optional<Patient> findByDni(String dni);

}

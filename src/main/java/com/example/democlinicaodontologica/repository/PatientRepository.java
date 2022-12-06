package com.example.democlinicaodontologica.repository;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PatientRepository extends JpaRepository<Patient,Long> {

    /*Optional<Patient> findByDni(String dni);*/

}

package com.example.democlinicaodontologica.service;


import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<PatientDto> updatePatient(Patient p);
    void deletePatient(Long id);
    Optional<List<PatientDto>> findAll();
    Optional<PatientDto> findPatient(Long id);
    Optional<PatientDto> addPatient(Patient patient);
}

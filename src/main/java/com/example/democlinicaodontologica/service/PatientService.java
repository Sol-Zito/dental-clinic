package com.example.democlinicaodontologica.service;


import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.PatientDto;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<PatientDto> updatePatient(Patient p) throws ResourceNotfoundException;
    void deletePatient(Long id) throws ResourceNotfoundException;
    Optional<List<PatientDto>> findAll();
    Optional<PatientDto> findPatient(Long id);
    void addPatient(Patient patient) throws ResourceNotfoundException;
}

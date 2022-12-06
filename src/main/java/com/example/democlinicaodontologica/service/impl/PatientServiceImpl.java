package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import com.example.democlinicaodontologica.repository.PatientRepository;
import com.example.democlinicaodontologica.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientIdao;
    @Autowired
    public PatientServiceImpl(PatientRepository patientIdao) {
        this.patientIdao = patientIdao;
    }

    @Override
    public Optional<PatientDto> addPatient(Patient patient) {
        ObjectMapper objectMapper = new ObjectMapper();
        patient.setDateAdmission(new Date());
        patient.setResidence(new Residence());
        Patient patient1 = patientIdao.save(patient);
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patient1, PatientDto.class);
        return Optional.of(patientDto);
    }

    @Override
    public Optional<PatientDto> findPatient(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Patient> paciente = patientIdao.findById(id);
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(paciente, PatientDto.class);

        return Optional.of(patientDto);
    }

    @Override
    public Optional<List<PatientDto>> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Patient> patients = patientIdao.findAll();
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patients, PatientDto.class);
        return Optional.of(Collections.singletonList(patientDto));
    }

    @Override
    public void deletePatient(Long id) {
        patientIdao.deleteAllById(Collections.singleton(id));
    }

    @Override
    public Optional<PatientDto> updatePatient(Patient p) {
        ObjectMapper objectMapper = new ObjectMapper();
        Patient patient = patientIdao.save(p);
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patient, PatientDto.class);
        return Optional.of(patientDto);
    }

   /* //chequear
    public Optional<PatientDto> findByDni(String dni){
        ObjectMapper objectMapper = new ObjectMapper();
        Optional<Patient> patient1 = patientIdao.findByDni(dni);
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patient1, PatientDto.class);
        return Optional.of(patientDto);
    }*/

}

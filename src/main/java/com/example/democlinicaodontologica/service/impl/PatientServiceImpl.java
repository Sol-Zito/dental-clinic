package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import com.example.democlinicaodontologica.repository.PatientRepository;
import com.example.democlinicaodontologica.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    private final static Logger LOGGER = Logger.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientIdao;
    @Autowired
    public PatientServiceImpl(PatientRepository patientIdao) {
        this.patientIdao = patientIdao;
    }

    @Override
    public void addPatient(Patient patient) throws ResourceNotfoundException {
        if(Objects.nonNull(findPatient(patient.getId()))){
            patient.setDateAdmission(new Date());
            patient.setResidence(new Residence());
            patientIdao.save(patient);
        }else {
            LOGGER.error("paciente ya esxiste");
            throw  new ResourceNotfoundException("paciente ya esxiste");
        }
    }

    @Override
    public Optional<PatientDto> findPatient(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        Patient paciente = patientIdao.findById(id).get();
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
    public void deletePatient(Long id) throws ResourceNotfoundException {
        if(Objects.nonNull(findPatient(id))){
            patientIdao.deleteById(id);
        }else {
            LOGGER.error("paciente not found");
            throw  new ResourceNotfoundException("paciente not found");
        }

    }

    @Override
    public Optional<PatientDto> updatePatient(Patient p) throws ResourceNotfoundException {
        if(Objects.nonNull(findPatient(p.getId()))){
        ObjectMapper objectMapper = new ObjectMapper();
        Patient patient = patientIdao.save(p);
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patient, PatientDto.class);
        return Optional.of(patientDto);
        }else {
            LOGGER.error("paciente not found");
            throw  new ResourceNotfoundException("paciente not found");
        }

    }

    public Optional<PatientDto> findByDni(String dni) {
        ObjectMapper objectMapper = new ObjectMapper();
        Patient patient1 = patientIdao.findByDni(dni).get();
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patient1, PatientDto.class);
        LOGGER.error("paciente found");
        return Optional.of(patientDto);
    }

}

package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import com.example.democlinicaodontologica.repository.PatientRepository;
import com.example.democlinicaodontologica.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
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
    private final static Logger LOGGER = Logger.getLogger(PatientServiceImpl.class);

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
        Optional<Patient> patient = patientIdao.findById(id);
        PatientDto patientDto;
        patientDto = objectMapper.convertValue(patient, PatientDto.class);

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
        if (patientIdao.findById(id).isEmpty()){
            LOGGER.error("patient not found to remove");
            throw new ResourceNotfoundException("doesn't exist dentist with this id ");
        }else {
            LOGGER.info("patient is already removed");
            patientIdao.deleteById(id);
        }

    }

    @Override
    public Optional<PatientDto> updatePatient(Patient p) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (patientIdao.findById(p.getId()).isPresent()){
            Patient patient = patientIdao.save(p);
            PatientDto patientDto;
            patientDto = objectMapper.convertValue(patient, PatientDto.class);
            LOGGER.info("patient updated: " + patientDto);
            return Optional.of(patientDto);
        }else {
            LOGGER.error("patient not found");
            throw new ResourceNotfoundException("patient not found");
        }

    }

   //chequear
    public Optional<PatientDto> findByDni(String dni) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        PatientDto patientDto = null;
        Optional<Patient> patient1 = patientIdao.findByDni(dni);
        if (patient1.isEmpty()){
            System.out.println(patient1);
            LOGGER.error("patient not found");
            throw new ResourceNotfoundException("patient not found");
        }else {
            patientDto = objectMapper.convertValue(patient1, PatientDto.class);
            System.out.println(patientDto);
            return Optional.of(patientDto);
        }

    }

}

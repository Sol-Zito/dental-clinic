package com.example.democlinicaodontologica.service;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.dto.DentistDto;

import java.util.List;
import java.util.Optional;

public interface DentistService {
    void addDentist(Dentist dentist) throws ResourceNotfoundException;
    void delete(Long id) throws ResourceNotfoundException;
    Optional<DentistDto> find(Long id) throws ResourceNotfoundException;
    List<Optional<DentistDto>> findAll();
    Optional<DentistDto> update(Dentist dentist) throws ResourceNotfoundException;

    Optional<DentistDto> findByTuition(Integer tuition) throws ResourceNotfoundException;
    Optional<DentistDto> findByLastname(String lastname) throws ResourceNotfoundException;
}

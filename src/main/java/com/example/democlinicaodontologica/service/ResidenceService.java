package com.example.democlinicaodontologica.service;

import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.dto.ResidenceDto;

import java.util.List;
import java.util.Optional;

public interface ResidenceService {
    Optional<ResidenceDto> addResidence(Residence residence);
    Optional<ResidenceDto> findResidence(Long id);
    List<Optional<ResidenceDto>> findAll();
    void deleteResidence(Long id);
    Optional<ResidenceDto> update(Residence residence);
}

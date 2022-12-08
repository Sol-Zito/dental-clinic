package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.dto.ResidenceDto;
import com.example.democlinicaodontologica.repository.ResidenceRepository;
import com.example.democlinicaodontologica.service.ResidenceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResidenceServiceImpl implements ResidenceService {
    private final static Logger LOGGER = Logger.getLogger(ResidenceServiceImpl.class);

    private final ResidenceRepository residenceIdao;

    @Autowired
    public ResidenceServiceImpl(ResidenceRepository residenceIdao) {
        this.residenceIdao = residenceIdao;
    }


    @Override
    public Optional<ResidenceDto> addResidence(Residence residence) {
        ObjectMapper objectMapper1 = new ObjectMapper();
        Residence residence2 = residenceIdao.save(residence);
        ResidenceDto residenceDto1;
        residenceDto1 = objectMapper1.convertValue(residence2, ResidenceDto.class);
        LOGGER.info("resident add");
        return Optional.ofNullable(residenceDto1);
    }

    @Override
    public Optional<ResidenceDto> findResidence(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        Residence domicilio1 = residenceIdao.findById(id).get();
        ResidenceDto residenceDto;
        residenceDto = objectMapper.convertValue(domicilio1, ResidenceDto.class);
        LOGGER.info("resident: " + residenceDto);
        return Optional.of(residenceDto);
    }

    @Override
    public List<Optional<ResidenceDto>> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Residence> residences = residenceIdao.findAll();
        ResidenceDto residenceDto;
        residenceDto = objectMapper.convertValue(residences, ResidenceDto.class);
        return (List<Optional<ResidenceDto>>) residenceDto;
    }

    @Override
    public void deleteResidence(Long id) throws ResourceNotfoundException {
        if (residenceIdao.findById(id).isPresent()){
            residenceIdao.deleteById(id);
            LOGGER.info("resident removed");
        }else {
            LOGGER.error("resident not found");
            throw new ResourceNotfoundException("resident not found");
        }

    }

    @Override
    public Optional<ResidenceDto> update(Residence residence) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (residenceIdao.findById(residence.getId()).isPresent()) {
            Residence residence1 = residenceIdao.save(residence);
            ResidenceDto residenceDto;
            residenceDto = objectMapper.convertValue(residence1, ResidenceDto.class);
            return Optional.of(residenceDto);
        }else {
            LOGGER.error("resident not found");
            throw new ResourceNotfoundException("resident not found");
        }
    }

}

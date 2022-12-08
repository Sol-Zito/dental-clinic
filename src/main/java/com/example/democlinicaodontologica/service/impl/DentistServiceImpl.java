package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.dto.DentistDto;
import com.example.democlinicaodontologica.repository.DentistRepository;
import com.example.democlinicaodontologica.service.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DentistServiceImpl implements DentistService {

    private final static Logger LOGGER = Logger.getLogger(DentistServiceImpl.class);

    private final DentistRepository dentistIdao;

    @Autowired
    public DentistServiceImpl(DentistRepository dentistIdao) {
        this.dentistIdao = dentistIdao;
    }



    @Override
    public Optional<DentistDto> addDentist(Dentist dentist) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if(dentistIdao.findByTuition(dentist.getTuition()).isPresent()){
            LOGGER.error("Denstist already exist, error in addDentist");
            throw new ResourceNotfoundException("Denstist already exist");
        }else {
            Dentist dentist1 = dentistIdao.save(dentist);
            DentistDto dentistDto;
            dentistDto = objectMapper.convertValue(dentist1, DentistDto.class);
            LOGGER.info("dentits was added");
            return Optional.of(dentistDto);
        }
    }

    @Override
    public void delete(Long id) throws ResourceNotfoundException {
        if (dentistIdao.findById(id).isEmpty()){
            LOGGER.error("dentist not found to remove");
            throw new ResourceNotfoundException("doesn't exist dentist with this id ");
        }else {
            LOGGER.info("dentist is already removed");
            dentistIdao.deleteById(id);
        }
    }

    @Override
    public Optional<DentistDto> find(Long id) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        // todavia no funciona
        DentistDto dentistDto;
        if(Objects.nonNull(dentistIdao.findById(id))){
            Dentist dentist = dentistIdao.findById(id).get();
            dentistDto = objectMapper.convertValue(dentist, DentistDto.class);
            LOGGER.info("Dentist found: " + dentistDto.toString());
            return Optional.of(dentistDto);
        } else {
            LOGGER.error("Dentist don't exist with this id");
            throw new ResourceNotfoundException("Dentist don't exist with this id");
        }
    }

    @Override
    public List<Optional<DentistDto>> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Dentist> dentist1 = dentistIdao.findAll();
        DentistDto dentistDto;
        dentistDto = objectMapper.convertValue(dentist1, DentistDto.class);
        return (List<Optional<DentistDto>>) dentistDto;
    }

    @Override
    public Optional<DentistDto> update(Dentist dentist) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if(Objects.nonNull(dentistIdao.findById(dentist.getId()))){
            Dentist dentist1 = dentistIdao.save(dentist);
            DentistDto dentistDto;
            dentistDto = objectMapper.convertValue(dentist1, DentistDto.class);
            LOGGER.info("Dentist updated: " + dentistDto.toString());
            return Optional.of(dentistDto);
        }else {
            LOGGER.error("Dentist is not found for the update");
            throw new ResourceNotfoundException("Dentist don't exist for the update");
        }
    }

    @Override
    public Optional<DentistDto> findByTuition(Integer tuition) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (dentistIdao.findByTuition(tuition).isEmpty()){
            LOGGER.error("Dentist don't exist with this tuition");
            throw new ResourceNotfoundException("Dentist don't existe with this tuition");
        } else {
            DentistDto dentistDto1;
            Dentist dentist = dentistIdao.findByTuition(tuition).get();
            dentistDto1 = objectMapper.convertValue(dentist, DentistDto.class);
            LOGGER.info("Dentist exist: " + dentistDto1.toString());
            return Optional.of(dentistDto1);
        }
    }

    @Override
    public Optional<DentistDto> findByLastname(String lastname) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if(dentistIdao.findByLastname(lastname).isEmpty()){
            throw new ResourceNotfoundException("Dentist don't exist");
        }else {
            DentistDto dentistDto;
            Dentist dentist = dentistIdao.findByLastname(lastname).get();
            dentistDto = objectMapper.convertValue(dentist, DentistDto.class);
            return Optional.of(dentistDto);
        }

    }



}

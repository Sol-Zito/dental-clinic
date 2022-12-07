package com.example.democlinicaodontologica.controller;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Residence;
import com.example.democlinicaodontologica.model.dto.ResidenceDto;
import com.example.democlinicaodontologica.service.impl.ResidenceServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residence")
public class ResidenceController {

    private final static Logger LOGGER = Logger.getLogger(ResidenceController.class);
    private final ResidenceServiceImpl residenceService;

    @Autowired
    public ResidenceController(ResidenceServiceImpl residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping("/{findResidence}")
    private ResponseEntity<ResidenceDto> findResidence(@PathVariable Long residenceId) {
        ResidenceDto residenceDto =  residenceService.findResidence(residenceId).orElse(null);
        LOGGER.info("Se ejecuto el findResidence en controller");
        return ResponseEntity.ok(residenceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResidence(@PathVariable Long id) throws ResourceNotfoundException {
        ResponseEntity<String> response = null;
        if (residenceService.findResidence(id).isPresent()) {
            residenceService.deleteResidence(id);
            LOGGER.info("Se ejecuto el deleteResidence en controller id: "+id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            LOGGER.error("No se ejecuto el deleteResidence en controller id: " +id);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PostMapping("/newResidence")
    public ResponseEntity newResidence(@RequestBody Residence residence) throws ResourceNotfoundException {
        LOGGER.info("Se ejecuto newResidence en controller residence" + residence.toString());
        residenceService.addResidence(residence);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/updateResidence")
    private ResponseEntity<ResidenceDto> updateResidence(@RequestBody Residence residence) throws ResourceNotfoundException {
        ResponseEntity response = null;
        if (residenceService.findResidence(residence.getId()).isPresent()) {
            residenceService.update(residence);
            LOGGER.info("Se ejecuto el updateResidence en controller" + residence.toString());
            response = ResponseEntity.status(HttpStatus.OK).body("Actualizado");
        } else {
            LOGGER.error("Error al querer updateResidence en controller" + residence.toString());
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }


}

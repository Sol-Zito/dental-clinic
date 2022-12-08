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

import java.util.Optional;


@RestController
@RequestMapping("/residence")
public class ResidenceController {

    private final static Logger LOGGER = Logger.getLogger(ResidenceController.class);

    @Autowired
    private ResidenceServiceImpl residenceService;

    @GetMapping(path = "/id/{id}")
    private ResponseEntity<ResidenceDto> findResidence(@PathVariable Long residenceId) {
        ResidenceDto residenceDto =  residenceService.findResidence(residenceId).get();
        LOGGER.info("Se ejecuto el findResidence en controller");
        return ResponseEntity.ok(residenceDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResidence(@PathVariable Long id) throws ResourceNotfoundException {
        ResponseEntity<String> response = null;
        residenceService.deleteResidence(id);
        LOGGER.info("Se ejecuto el deleteResidence en controller id: " + id);
        response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");

        return response;
    }

    @PostMapping("/newResidence")
    public ResponseEntity newResidence(@RequestBody Residence residence) {
        LOGGER.info("Se ejecuto newResidence en controller residence" + residence);
        Optional<ResidenceDto> residenceDto = residenceService.addResidence(residence);
        return ResponseEntity.status(HttpStatus.OK).body(residenceDto);
    }

    @PutMapping("/updateResidence")
    private ResponseEntity<ResidenceDto> updateResidence(@RequestBody Residence residence) throws ResourceNotfoundException {
        ResponseEntity response = null;
        residenceService.update(residence);
        LOGGER.info("Se ejecuto el updateResidence en controller" + residence);
        response = ResponseEntity.status(HttpStatus.OK).body("Actualizado: " + residence);
        return response;
    }

}

package com.example.democlinicaodontologica.controller;
import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.dto.DentistDto;
import com.example.democlinicaodontologica.service.impl.DentistServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private final static Logger LOGGER = Logger.getLogger(DentistController.class);

    private final DentistServiceImpl dentistService;

    @Autowired
    public DentistController(DentistServiceImpl dentistService) {
        this.dentistService = dentistService;
    }

    // GET MAPPING
    @GetMapping("/saludo")
    private String saludo()  {
        return "Buenas odontologos";
    }

    @PostMapping("/newDentist")
    public ResponseEntity<String> newDentist(@RequestBody Dentist dentist) throws ResourceNotfoundException {
        ResponseEntity response = null;
        dentistService.addDentist(dentist);
        LOGGER.info("newDentist in DentisController");
        response = ResponseEntity.ok("dentist was added: " + dentist);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) throws ResourceNotfoundException {
        ResponseEntity<String> response = null;
            dentistService.delete(id);
            response = ResponseEntity.ok("dentist is already removed");
            LOGGER.info("dentist is already removed, DentistController");

        return response;
    }

    @GetMapping(path = "/id/{id}")
    private ResponseEntity findDentist(@PathVariable("id") Long dentistId) throws ResourceNotfoundException {
        DentistDto dentistDto =  dentistService.find(dentistId).get();
        LOGGER.info("Se ejecuto find dentist" + dentistDto);
        return ResponseEntity.ok(dentistDto);
    }

    @PutMapping("/updateDentist")
    private ResponseEntity updateDentist(@RequestBody Dentist dentist) throws ResourceNotfoundException {
        dentistService.update(dentist);
        LOGGER.info("dentits updated, DenstisController, dentist: " + dentist);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }

    @GetMapping("/listDentist")
    private List<Optional<DentistDto>> listDentist(){
        return dentistService.findAll();
    }

    @GetMapping(path = "/Lastname/{lastname}")
    public ResponseEntity findByLastname(@PathVariable String lastname) throws ResourceNotfoundException {
        DentistDto dentistDto= dentistService.findByLastname(lastname).get();
        return ResponseEntity.ok(dentistDto);
    }

}

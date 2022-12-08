package com.example.democlinicaodontologica.controller;
import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import com.example.democlinicaodontologica.service.impl.PatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final static Logger LOGGER = Logger.getLogger(PatientController.class);

    @Autowired
    private PatientServiceImpl patientService;

    @GetMapping("/saludo")
    private String saludo()  {
        return "Buenas paciente";
    }

    @GetMapping("/listPatient")
    private Optional<List<PatientDto>> listPatient() {
        return patientService.findAll();
    }

    @GetMapping(path = "/id/{id}")
    private ResponseEntity<PatientDto> findPatient(@PathVariable Long patientId) {
        ResponseEntity response = null;
        PatientDto patientDto = patientService.findPatient(patientId).orElse(null);
        LOGGER.info("Se ejecuto findPatient patientId" + patientDto);
        response= ResponseEntity.status(HttpStatus.OK).body(patientDto);
       return response;
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deletePatient(@PathVariable Long patientId) throws ResourceNotfoundException {
        ResponseEntity response = null;
        patientService.deletePatient(patientId);
        LOGGER.info("Se ejecuto deletePatient patientId" + patientId);
        response = ResponseEntity.status(HttpStatus.OK).body("Eliminado");
        return response;
    }

    @PostMapping("/newPatient")
    private ResponseEntity<String> newPatient(@RequestBody Patient patient) {
        ResponseEntity response = null;
        Optional<PatientDto> patient1 = patientService.addPatient(patient);
        LOGGER.info("Se ejecuto newPatient patientId" + patient1);
        response = ResponseEntity.ok("se agrego patient: " + patient1);
        return response;
    }


    @PutMapping("/updatePatient")
    private ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws ResourceNotfoundException {
        ResponseEntity response = null;
        patientService.updatePatient(patient);
        LOGGER.info("Se ejecuto updatePatient patientId" + patient);
        response = ResponseEntity.status(HttpStatus.OK).body("Actualizado");
        return response;
    }

    @GetMapping(path = "/dni/{dni}")
    public ResponseEntity findPatientByDni( @PathVariable String dni) throws ResourceNotfoundException {
        //devuelve valores null
        ResponseEntity response = null;
        PatientDto patientDto = patientService.findByDni(dni).get();
        LOGGER.info("Se ejecuto findPatientByDni patientId" + patientDto);
        response = ResponseEntity.status(HttpStatus.FOUND).body(patientDto);
        return response;
    }

}

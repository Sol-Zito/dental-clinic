package com.example.democlinicaodontologica.controller;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.dto.BookingDto;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import com.example.democlinicaodontologica.service.impl.PatientServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final static Logger LOGGER = Logger.getLogger(PatientController.class);

    @Autowired
    private PatientServiceImpl patientService;

    // GET MAPPING
    @GetMapping("/saludo")
    private String saludo()  {
        return "Buenas paciente";
    }

    @GetMapping("/listPatient")
    private Optional<List<PatientDto>> listPatient() throws SQLException {
        return patientService.findAll();
    }

    //chequear si funciona con el if/else
    @GetMapping("/findPatient")
    private ResponseEntity<PatientDto> findPatient(@PathVariable Long patientId) throws SQLException {
        ResponseEntity response = null;
       if (patientService.findPatient(patientId).isPresent()){
           PatientDto patientDto = patientService.findPatient(patientId).orElse(null);
           LOGGER.info("Se ejecuto findPatient patientId" + patientDto.toString());
           response= ResponseEntity.status(HttpStatus.FOUND).build();
       }else {
           LOGGER.error("Error al ejecutar findPatient patientId" + patientId);
           response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
       return response;
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deletePatient(@PathVariable("patientId") Long patientId) throws SQLException {
        ResponseEntity response = null;
        if (patientService.findPatient(patientId).isPresent()) {
            patientService.deletePatient(patientId);
            LOGGER.info("Se ejecuto deletePatient patientId" + patientId);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            LOGGER.error("Error al ejecutar deletePatient patientId" + patientId);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    /*//chequear si asi no permite replica
    @PostMapping("/newPatient")
    private ResponseEntity newPatient(@RequestBody Patient patient) throws SQLException {
        ResponseEntity response = null;
        if (patientService.findByDni(patient.getDni()).isPresent()){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patiente exist");
            LOGGER.error("El paciente ya existe");
        }else{
            patientService.addPatient(patient);
            response = ResponseEntity.ok("Patient add");
            LOGGER.info("Se ejecuto newPatient patientId" + patient.toString());
        }

        return response;
    }*/

    @PostMapping("/newPatient")
    public ResponseEntity newPatient(@RequestBody Patient patient) throws SQLException {
        LOGGER.info("se ejecuto newPatient en PatientController");
        return ResponseEntity.ok(patientService.addPatient(patient));
    }

    @PutMapping("/updatePatient")
    private ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws SQLException {
        ResponseEntity response = null;
        if (patientService.findPatient(patient.getId()).isPresent()) {
            patientService.updatePatient(patient);
            LOGGER.info("Se ejecuto updatePatient patientId" + patient.toString());
            response = ResponseEntity.status(HttpStatus.OK).body("Actualizado");
        } else {
            LOGGER.error("Error al ejecutar updatePatient patientId");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    /*//chequear si funciona con el if/else
    @GetMapping("/findPatientByDni")
    public ResponseEntity<PatientDto> findPatientByDni( @PathVariable String dni){
        ResponseEntity response = null;
        if (patientService.findByDni(dni).isPresent()){
            Optional<PatientDto> patientDto = patientService.findByDni(dni);
            LOGGER.info("Se ejecuto findPatientByDni patientId" + patientDto.toString());
            response= ResponseEntity.status(HttpStatus.FOUND).build();
        }else {
            LOGGER.error("Error al ejecutar findPatient patientId"+ dni);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("patient not found ");
        }
        return response;
    }*/


}

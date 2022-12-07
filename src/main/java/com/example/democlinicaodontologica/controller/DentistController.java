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
    public ResponseEntity newDentist(@RequestBody Dentist dentist) throws ResourceNotfoundException {
        LOGGER.info("newDentist in DenstisController");
        dentistService.addDentist(dentist);
        return ResponseEntity.ok("dentist was added");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentist(@PathVariable Long id) throws ResourceNotfoundException {
        ResponseEntity<String> response = null;
        /*if (dentistService.find(id).isPresent()) {*/
            dentistService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("dentist is already removed");
            LOGGER.info("dentist is already removed, DentistController");
       /* } else {
            LOGGER.error("no se puedo ejecuta deleteDentist en DenstisController ");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }*/

        return response;
    }

    @GetMapping("/{findDentist}")
    private ResponseEntity findDentist(@PathVariable("id") Long dentistId) throws ResourceNotfoundException {
        Optional<DentistDto> dentistDto =  dentistService.find(dentistId);
        return ResponseEntity.ok(dentistDto);
    }

    @PutMapping("/updateDentist")
    private ResponseEntity updateDentist(@RequestBody Dentist dentist) throws ResourceNotfoundException {
       ResponseEntity response = null;
        if (dentistService.find(dentist.getId()).isPresent()) {
            dentistService.update(dentist);
            LOGGER.info("se ejecuto updateDentist en DenstisController, dentist: " + dentist.toString());
            response = ResponseEntity.status(HttpStatus.OK).body("Actualizado");
        } else {
            LOGGER.error("no se puedo ejecutar updateDentist en DenstisController");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/listDentist")
    private List<Optional<DentistDto>> listDentist(){
        return dentistService.findAll();
    }

    @GetMapping("/findByLastname")
    public ResponseEntity findByName(@PathVariable String lastname) throws ResourceNotfoundException {
        Optional<DentistDto> dentist = dentistService.findByLastname(lastname);
        return ResponseEntity.ok(dentist);
    }



}

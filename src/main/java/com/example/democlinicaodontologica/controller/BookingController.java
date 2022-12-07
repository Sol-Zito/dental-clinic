package com.example.democlinicaodontologica.controller;
import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Booking;
import com.example.democlinicaodontologica.model.dto.BookingDto;
import com.example.democlinicaodontologica.service.impl.BookingServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final static Logger LOGGER = Logger.getLogger(BookingController.class);

    private final BookingServiceImpl bookingService;
    @Autowired
    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/{id}")
    private ResponseEntity<BookingDto> findBooking(@PathVariable Long turnoId) {
        BookingDto bookingDto =  bookingService.findBooking(turnoId).orElse(null);
        LOGGER.info("Se ejecuto el findBooking en controller");
        return ResponseEntity.ok(bookingDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) throws ResourceNotfoundException {
        ResponseEntity<String> response = null;
        if (bookingService.findBooking(id).isPresent()) {
            bookingService.deleteBooking(id);
            LOGGER.info("Se ejecuto el deleteBooking en controller id: "+id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            LOGGER.error("No se ejecuto el deleteBooking en controller id: " +id);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @PostMapping("/newBooking")
    public ResponseEntity<Optional<BookingDto>> newBooking(@RequestBody Booking booking) throws ResourceNotfoundException {
        LOGGER.info("Se ejecuto newBooking en controller booking" + booking.toString());
        return ResponseEntity.ok(bookingService.addBooking(booking));
    }

    @PutMapping("/updateBooking")
    private ResponseEntity<BookingDto> updateBooking(@RequestBody Booking booking)  {
        ResponseEntity response = null;
        if (bookingService.findBooking(booking.getId()).isPresent()) {
            bookingService.update(booking);
            LOGGER.info("Se ejecuto el updateBooking en controller booking" + booking.toString());
            response = ResponseEntity.status(HttpStatus.OK).body("Actualizado");
        } else {
            LOGGER.error("Error al querer updateBooking en controller booking" + booking.toString());
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }


}

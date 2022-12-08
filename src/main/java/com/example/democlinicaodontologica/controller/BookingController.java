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

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final static Logger LOGGER = Logger.getLogger(BookingController.class);

    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/id/{id}")
    private ResponseEntity<BookingDto> findBooking(@PathVariable Long turnoId)  {
        BookingDto bookingDto =  bookingService.findBooking(turnoId).get();
        LOGGER.info("Se ejecuto el findBooking en controller");
        return ResponseEntity.ok(bookingDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) throws ResourceNotfoundException {
        bookingService.deleteBooking(id);
        LOGGER.info("Se ejecuto el deleteBooking en controller id: "+id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
    }

    @PostMapping("/newBooking")
    public ResponseEntity newBooking(@RequestBody Booking booking)  {
        LOGGER.info("Se ejecuto newBooking en controller booking" + booking);
         bookingService.addBooking(booking);
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    @PutMapping("/updateBooking")
    private ResponseEntity<BookingDto> updateBooking(@RequestBody Booking booking) throws ResourceNotfoundException {
        ResponseEntity response = null;

            bookingService.update(booking);
            LOGGER.info("Se ejecuto el updateBooking en controller booking" + booking);
            response = ResponseEntity.status(HttpStatus.OK).body("Actualizado");

        return response;
    }


}

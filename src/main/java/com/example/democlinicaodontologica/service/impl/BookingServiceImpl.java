package com.example.democlinicaodontologica.service.impl;
import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Booking;
import com.example.democlinicaodontologica.model.dto.BookingDto;
import com.example.democlinicaodontologica.repository.BookingRepository;
import com.example.democlinicaodontologica.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {


    private final static Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingIdao;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingIdao) {
        this.bookingIdao = bookingIdao;
    }

    @Override
    public Optional<BookingDto> addBooking(Booking booking) {
        ObjectMapper objectMapper = new ObjectMapper();
        Booking booking1 = bookingIdao.save(booking);
        booking1.setDentist(booking.getDentist());
        booking1.setPatient(booking.getPatient());
        booking1.setDateTime(new Date());
        BookingDto bookingDto;
        bookingDto = objectMapper.convertValue(booking1, BookingDto.class);
        LOGGER.info("addBooking in DentistController, booking: " + booking);
        return Optional.of(bookingDto);
    }

    @Override
    public Optional<BookingDto> findBooking(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();

        Optional<Booking> t = bookingIdao.findById(id);

        BookingDto bookingDto;

        bookingDto = objectMapper.convertValue(t, BookingDto.class);
        LOGGER.info("findBooking in DentistController, booking: " + bookingDto);
        return Optional.of(bookingDto);
    }

    @Override
    public void deleteBooking(Long id) throws ResourceNotfoundException {
        if (bookingIdao.findById(id).isEmpty()){
            LOGGER.error("booking not found to remove");
            throw new ResourceNotfoundException("booking don't exist dentist with this id ");
        }else {
            LOGGER.info("booking is already removed");
            bookingIdao.deleteById(id);
        }
    }

    @Override
    public Optional<BookingDto> update(Booking booking) throws ResourceNotfoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (bookingIdao.findById(booking.getId()).isEmpty()){
            LOGGER.error("booking not found to remove");
            throw new ResourceNotfoundException("booking don't exist dentist with this id ");
        }else {
            Booking booking1 = bookingIdao.save(booking);
            BookingDto bookingDto;
            bookingDto = objectMapper.convertValue(booking1, BookingDto.class);
            return Optional.of(bookingDto);
        }
    }

}


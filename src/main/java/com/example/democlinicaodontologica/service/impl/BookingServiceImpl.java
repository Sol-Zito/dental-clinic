package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.controller.BookingController;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.Booking;
import com.example.democlinicaodontologica.model.dto.BookingDto;
import com.example.democlinicaodontologica.repository.BookingRepository;
import com.example.democlinicaodontologica.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        booking.setDentist(new Dentist());
        booking.setPatient(new Patient());

        Booking booking1 = bookingIdao.save(booking);
        BookingDto bookingDto;
        bookingDto = objectMapper.convertValue(booking1, BookingDto.class);
        LOGGER.info("addBooking in DentistController, booking: " + booking);
        return Optional.of(bookingDto);
    }
    @Override
    public Optional<BookingDto> findBooking(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();

        Booking t = (Booking) bookingIdao.findAllById(Collections.singleton(id));

        BookingDto bookingDto;

        bookingDto = objectMapper.convertValue(t, BookingDto.class);
        LOGGER.info("findBooking in DentistController, booking: " + bookingDto);
        return Optional.of(bookingDto);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingIdao.deleteById(id);
    }

    @Override
    public Optional<BookingDto> update(Booking booking) {
        ObjectMapper objectMapper = new ObjectMapper();
        Booking booking1 = bookingIdao.save(booking);
        BookingDto bookingDto;
        bookingDto = objectMapper.convertValue(booking1, BookingDto.class);
        return Optional.of(bookingDto) ;
    }

}


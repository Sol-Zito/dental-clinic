package com.example.democlinicaodontologica.service.impl;

import com.example.democlinicaodontologica.exceptions.ResourceNotfoundException;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.Patient;
import com.example.democlinicaodontologica.model.Booking;
import com.example.democlinicaodontologica.model.dto.BookingDto;
import com.example.democlinicaodontologica.model.dto.DentistDto;
import com.example.democlinicaodontologica.model.dto.PatientDto;
import com.example.democlinicaodontologica.repository.BookingRepository;
import com.example.democlinicaodontologica.repository.DentistRepository;
import com.example.democlinicaodontologica.repository.PatientRepository;
import com.example.democlinicaodontologica.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {


    private final static Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingIdao;
    private final DentistRepository dentistRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingIdao, DentistRepository dentistRepository, PatientRepository patientRepository) {
        this.bookingIdao = bookingIdao;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Optional<BookingDto> addBooking(Booking booking) throws ResourceNotfoundException {
        Optional<Dentist> dentist = dentistRepository.findById(booking.getDentist().getId());
        Optional<Patient> patient = patientRepository.findById(booking.getPatient().getId());

        if (patient.isEmpty() || dentist.isEmpty()) {
            throw new ResourceNotfoundException("El patient o el dentist no existen");
        }
        booking.setDentist(dentist.get());
        booking.setPatient(patient.get());

        booking.setDateTime(new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        booking.setDentist(new Dentist());
        booking.setPatient(new Patient());

        Booking booking1 = bookingIdao.save(booking);
        BookingDto bookingDto;
        bookingDto = objectMapper.convertValue(booking1, BookingDto.class);
        LOGGER.info("addBooking booking: " + booking);
        return Optional.of(bookingDto);
    }
    @Override
    public Optional<BookingDto> findBooking(Long id) {
        ObjectMapper objectMapper = new ObjectMapper();

        Booking t = (Booking) bookingIdao.findAllById(Collections.singleton(id));

        BookingDto bookingDto;

        bookingDto = objectMapper.convertValue(t, BookingDto.class);
        LOGGER.info("find Booking  booking: " + bookingDto);
        return Optional.of(bookingDto);
    }

    @Override
    public void deleteBooking(Long id) throws ResourceNotfoundException {
        if (bookingIdao.findById(id).isPresent()){
            bookingIdao.deleteById(id);
        }else {
            LOGGER.info("booking not found");
            throw new ResourceNotfoundException("booking not found");
        }

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


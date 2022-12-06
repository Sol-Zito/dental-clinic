package com.example.democlinicaodontologica.service;


import com.example.democlinicaodontologica.model.Booking;
import com.example.democlinicaodontologica.model.dto.BookingDto;

import java.util.Optional;

public interface BookingService {
    Optional<BookingDto> addBooking(Booking booking);
    Optional<BookingDto> findBooking(Long id);
    void deleteBooking(Long id);
    Optional<BookingDto> update(Booking booking);
}

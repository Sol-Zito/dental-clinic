package com.example.democlinicaodontologica.repository;
import com.example.democlinicaodontologica.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

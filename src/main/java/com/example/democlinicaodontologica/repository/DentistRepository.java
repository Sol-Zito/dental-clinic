package com.example.democlinicaodontologica.repository;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.dto.DentistDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface DentistRepository extends JpaRepository<Dentist,Long> {

    @Query("SELECT id, name, lastname, tuition FROM Dentist WHERE lastname=?1")
   Optional<Dentist> findByLastname(String lastname);

    @Query("SELECT id, lastname, name FROM Dentist WHERE tuition=?1")
    Optional<Dentist> findByTuition(Integer tuition);


}

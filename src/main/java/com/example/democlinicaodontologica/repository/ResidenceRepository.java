package com.example.democlinicaodontologica.repository;
import com.example.democlinicaodontologica.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface ResidenceRepository extends JpaRepository<Residence, Long> {

}

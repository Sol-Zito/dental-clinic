package com.example.democlinicaodontologica.model.dto;
import com.example.democlinicaodontologica.model.Dentist;
import com.example.democlinicaodontologica.model.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDto {
    private Long id;
    private Date dateTime;
    private Patient patient;
    private Dentist dentist;
    private boolean confirmdate;

    public BookingDto() {
    }

    public BookingDto(Long id, Date dateTime, Patient patient, Dentist dentist, boolean confirmdate) {
        this.id = id;
        this.dateTime = dateTime;
        this.patient = patient;
        this.dentist = dentist;
        this.confirmdate = confirmdate;
    }
}

package com.example.democlinicaodontologica.model.dto;

import com.example.democlinicaodontologica.model.Residence;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDto {

    private String name;
    private String lastname;
    private Date dateAdmission;
    private Residence residence;

    public PatientDto() {
    }

    public PatientDto(String name, String lastname, Date dateAdmission, Residence residence) {

        this.name = name;
        this.lastname = lastname;
        this.dateAdmission = dateAdmission;
        this.residence = residence;
    }
}

package com.example.democlinicaodontologica.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDto {

    private String name;
    private String lastname;


    public DentistDto() {
    }

    public DentistDto( String name, String lastname) {

        this.name = name;
        this.lastname = lastname;
    }
}

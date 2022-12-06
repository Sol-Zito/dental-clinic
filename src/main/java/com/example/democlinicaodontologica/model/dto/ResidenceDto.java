package com.example.democlinicaodontologica.model.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResidenceDto {
    private Long id;
    private String street;
    private String number;
    private String location;
    private String province;

    public ResidenceDto() {
    }

    public ResidenceDto(Long id, String street, String number, String location, String province) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }
}

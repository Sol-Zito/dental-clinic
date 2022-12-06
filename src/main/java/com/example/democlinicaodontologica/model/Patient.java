package com.example.democlinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(appliesTo = "patient")
@Getter
@Setter
@ToString
public class Patient implements Serializable {
    /* en caso de q exista la tabla
    @GeneratedValue(strategy = GenerationType.SEQUENCE) */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_patient", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "dni")
    private String dni;

    @Column(name = "dateAdmission")
    private Date dateAdmission;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_residence" , referencedColumnName = "id_residence")
    private Residence residence;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "patient")
    /*@JoinColumn(name = "id_booking", referencedColumnName = "id_booking")*/
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    public Patient() {
    }

    public Patient(Long id, String name, String lastname, String dni, Date dateAdmission, Residence residence) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.dateAdmission = dateAdmission;
        this.residence = residence;
    }

    public Patient(String name, String lastname, String dni, Date dateAdmission, Residence residence) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.dateAdmission = dateAdmission;
        this.residence = residence;
    }

}

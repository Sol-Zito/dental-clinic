package com.example.democlinicaodontologica.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(appliesTo = "booking")
@Getter
@Setter
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_booking", nullable = false)
    private Long id;

    @Column(name = "datetime")
    private Date dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_patient" , referencedColumnName = "id_patient")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dentist", referencedColumnName = "id_dentist")
    private Dentist dentist;

    @Column(name = "confirmdate")
    private boolean confirmdate;


    public Booking() {
    }

    public Booking(Date dateTime, Patient patient, Dentist dentist) {
        this.dateTime = dateTime;
        this.patient = patient;
        this.dentist = dentist;
    }

    public Booking(Long id, Date dateTime, boolean confirmdate) {
        this.id = id;
        this.dateTime = dateTime;
        this.confirmdate = confirmdate;
    }

}

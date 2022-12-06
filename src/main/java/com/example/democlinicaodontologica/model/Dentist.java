package com.example.democlinicaodontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(appliesTo = "dentist")
@Getter
@Setter
@ToString
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_dentist", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "tuition")
    private Integer tuition;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dentist")
    /*@JoinColumn(name = "id_booking", referencedColumnName = "id_booking")*/
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    public Dentist() {
    }

    public Dentist(String name, String lastname, Integer tuition) {
        this.name = name;
        this.lastname = lastname;
        this.tuition = tuition;
    }

    public Dentist(Long id, String name, String lastname, Integer tuition) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.tuition = tuition;
    }

}

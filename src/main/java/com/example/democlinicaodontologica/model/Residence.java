package com.example.democlinicaodontologica.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(appliesTo = "residence")
@Getter
@Setter
@ToString
public class Residence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_residence", nullable = false)
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "location")
    private String location;
    @Column(name = "province")
    private String province;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "residence")
    /*@JoinColumn(name = "id_patient", referencedColumnName = "id_patient")*/
    private Set<Patient> patients = new HashSet<>();

    public Residence() {
    }

    public Residence(Long id, String street, String number, String location, String province) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }
    public Residence(String street, String number, String location, String province) {
        this.street = street;
        this.number = number;
        this.location = location;
        this.province = province;
    }

}

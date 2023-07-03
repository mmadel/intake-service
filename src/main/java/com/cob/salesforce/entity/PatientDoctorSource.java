package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity(name = "patient_doctor_source")
@Getter
@Setter
public class PatientDoctorSource extends PatientDependencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "doctor_name")
    private String name;
    @Column(name = "npi")
    private String npi;

    @Column(name = "fax")
    private Long fax;
    @Column(name = "address")
    private String address;

    @Column
    private Long createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
}

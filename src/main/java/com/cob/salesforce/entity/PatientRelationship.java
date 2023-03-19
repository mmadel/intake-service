package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "patient_relationship")
@Getter
@Setter
public class PatientRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "patient_relationship_name")
    private String patientRelationshipName;

    @Column(name = "patient_relationship_phone")
    private String patientRelationshipPhone;
    @Column(name = "employer_name")
    private String employerName;
}

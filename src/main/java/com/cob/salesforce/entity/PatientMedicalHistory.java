package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Patient_medical_history")
@Getter
@Setter
public class PatientMedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;
    @Column(name = "evaluation_submission", length = 1000)
    private String evaluationSubmission;

    @Column(name = "medicationPrescription" , length = 1000)
    private String medicationPrescription;

    @Column(name = "patient_condition" , length = 500)
    private String patientCondition;

    @Column(name="scanning_test")
    private Boolean scanningTest;
    @Column(name="scanning_test_value")
    private String scanningTestValue;
    @Column(name="metal_implantation")
    private Boolean metalImplantation;
    @Column(name="pace_maker")
    private Boolean pacemaker;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}

package com.cob.salesforce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "patient_medical")
@Getter
@Setter
public class PatientMedical extends PatientDependencyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="family_result_submission")
    private Boolean familyResultSubmission;
    @Column(name="appointment_booking")
    private String appointmentBooking;
    @Column(name="primary_doctor")
    private String primaryDoctor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}

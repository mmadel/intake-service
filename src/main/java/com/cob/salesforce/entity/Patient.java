package com.cob.salesforce.entity;

import com.cob.salesforce.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "patient")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="patient_name")
    private String name;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_type")
    private PhoneType phoneType;
    @Column(name="phone")
    private String phone;

    @Column(name = "id_effective_from")
    private Long idEffectiveFrom;
    @Column(name = "id_effective_to")
    private Long idEffectiveTo;
    @Column(name="email")
    private String email;
    @Column(name="emergency_name")
    private String emergencyName;
    @Column(name="emergency_phone")
    private String emergencyPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_status")
    private EmploymentStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_source_type")
    private PatientSourceType patientSourceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "insurance_worker_type")
    private InsuranceWorkerType insuranceWorkerType;

    @Column(name = "Physical_therapy")
    private Boolean PhysicalTherapy;


}

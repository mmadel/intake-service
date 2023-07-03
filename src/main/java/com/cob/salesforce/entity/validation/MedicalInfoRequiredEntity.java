package com.cob.salesforce.entity.validation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class MedicalInfoRequiredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    Boolean recommendation;
    @Column
    Boolean recommendedDoctorName;
    @Column
    Boolean recommendedDoctorNpi;
    @Column
    Boolean recommendedDoctorFax;
    @Column
    Boolean recommendedDoctorAddress;
    @Column
    Boolean recommendedEntityName;
    @Column
    Boolean physicalTherapy;
    @Column
    Boolean physicalTherapyLocation;
    @Column
    Boolean physicalTherapyNumberOfVisit;
    @Column
    Boolean appointmentBooking;
    Boolean resultSubmissionFamily;
    Boolean primaryDoctor;
}

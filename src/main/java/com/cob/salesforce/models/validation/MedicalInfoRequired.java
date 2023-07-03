package com.cob.salesforce.models.validation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalInfoRequired {
    Long id;

    Boolean recommendation;

    Boolean recommendedDoctorName;

    Boolean recommendedDoctorNpi;

    Boolean recommendedDoctorFax;

    Boolean recommendedDoctorAddress;

    Boolean recommendedEntityName;

    Boolean physicalTherapy;

    Boolean physicalTherapyLocation;

    Boolean physicalTherapyNumberOfVisit;

    Boolean appointmentBooking;
    Boolean resultSubmissionFamily;
    Boolean primaryDoctor;
}

package com.cob.salesforce.models.intake.fields;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalInformation {
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

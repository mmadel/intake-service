package com.cob.salesforce.models.intake.medical;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientMedical  implements Serializable {
    private Boolean familyResultSubmission;
    private String appointmentBooking;
    private String primaryDoctor;

    private PatientMedicalHistory patientMedicalHistory;

    private PatientPhysicalTherapy patientPhysicalTherapy;
}

package com.cob.salesforce.models.intake.medical;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientMedical implements Serializable {
    private String appointmentBooking;
    private String primaryDoctor;

    private PatientMedicalHistory patientMedicalHistory;

    private PatientPhysicalTherapy patientPhysicalTherapy;
}

package com.cob.salesforce.models.intake.essentials;

import com.cob.salesforce.enums.Gender;
import com.cob.salesforce.enums.MaritalStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientEssentialInformation implements Serializable {
    private PatientName patientName;

    private Long dateOfBirth;

    private Gender gender;

    private PatientPhone patientPhone;

    private String email;
    private PatientAddress patientAddress;

    private MaritalStatus maritalStatus;

    private PatientEmergencyContact patientEmergencyContact;
    private PatientEmployment patientEmployment;
}

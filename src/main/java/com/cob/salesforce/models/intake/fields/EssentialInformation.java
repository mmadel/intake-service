package com.cob.salesforce.models.intake.fields;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EssentialInformation {

    Boolean firstName;
    Boolean middleName;
    Boolean lastName;
    Boolean birthDate;
    Boolean gender;
    Boolean maritalStatus;
    Boolean Phone;
    Boolean email;
    Boolean patientId;
    Boolean emergencyContact;
}

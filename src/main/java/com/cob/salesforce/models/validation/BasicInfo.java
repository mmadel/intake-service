package com.cob.salesforce.models.validation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasicInfo {
    Long id;
    Boolean name;
    Boolean birthDate;
    Boolean gender;
    Boolean  maritalStatus;
    Boolean Phone;
    Boolean  email;
    Boolean patientId;
    Boolean emergencyContact;
}

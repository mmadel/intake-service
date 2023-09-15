package com.cob.salesforce.models.intake.insurance.commercial;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientRelationship implements Serializable {
    private String patientRelationshipName;
    private String patientRelationshipFirstName;
    private String patientRelationshipMiddleName;
    private String patientRelationshipLastName;
    private String patientRelationshipPhone;
    private String employerName;
}

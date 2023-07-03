package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class PatientRelationshipDTO {

    private String patientRelationshipName;
    private String patientRelationshipFirstName;
    private String patientRelationshipMeddileName;
    private String patientRelationshipLastName;
    private String patientRelationshipPhone;
    private String employerName;

    public String getFullName() {
        return patientRelationshipFirstName + ',' + patientRelationshipMeddileName
                + ',' + patientRelationshipLastName;
    }
}

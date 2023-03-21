package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class PatientRelationshipDTO {

    private String patientRelationshipName;

    private String patientRelationshipPhone;
    private String employerName;
}

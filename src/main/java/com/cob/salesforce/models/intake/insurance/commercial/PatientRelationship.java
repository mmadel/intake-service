package com.cob.salesforce.models.intake.insurance.commercial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientRelationship implements Serializable {
    private String patientRelationshipName;
    private String patientRelationshipFirstName;
    private String patientRelationshipMiddleName;
    private String patientRelationshipLastName;
    private String patientRelationshipPhone;
    private String employerName;
}

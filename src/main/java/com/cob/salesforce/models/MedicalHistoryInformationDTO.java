
package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalHistoryInformationDTO {
    private Double height;
    private String heightUnit;
    private Double weight;
    private String evaluationSubmission;
    private String medicationPrescription;

    private Boolean scanningTest;
    private String scanningTestValue;

    private List<String> patientCondition;
    private Boolean metalImplantation;

    private Boolean pacemaker;

    private String surgeriesList;

}

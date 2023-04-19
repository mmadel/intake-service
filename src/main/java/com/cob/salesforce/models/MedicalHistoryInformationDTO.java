
package com.cob.salesforce.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class MedicalHistoryInformationDTO {
    private Double height;
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

package com.cob.salesforce.models.intake.medical;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientMedicalHistory implements Serializable {

    private Double height;

    private String heightUnit;

    private Double weight;


    private String weightUnit;

    private String evaluationSubmission;


    private String medicationPrescription;


    private List<String> patientCondition;

    private Boolean scanningTest;

    private String scanningTestValue;

    private Boolean metalImplantation;

    private Boolean pacemaker;

    private String surgeriesList;
}

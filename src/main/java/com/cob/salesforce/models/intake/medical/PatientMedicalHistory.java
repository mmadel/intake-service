package com.cob.salesforce.models.intake.medical;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientMedicalHistory implements Serializable {

    private Double height;

    private String heightUnit;

    private Double weight;


    private String weightUnit;

    private String evaluationSubmission;


    private String medicationPrescription;


    private String patientCondition;

    private Boolean scanningTest;

    private String scanningTestValue;

    private Boolean metalImplantation;

    private Boolean pacemaker;

    private String surgeriesList;
}

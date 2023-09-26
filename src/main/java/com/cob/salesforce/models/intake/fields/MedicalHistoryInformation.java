package com.cob.salesforce.models.intake.fields;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MedicalHistoryInformation {

    //Boolean height;
    //Boolean weight;
    Boolean evaluationReason;
    Boolean medicationPrescription;
    Boolean patientCondition;
    Boolean scanningTest;
    Boolean scanningTestValue;
    Boolean metalImplantation;
    Boolean pacemaker;
    Boolean surgeriesList;
}

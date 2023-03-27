package com.cob.salesforce.models.validation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedicalHistoryInfoRequired {
    Boolean height;
    Boolean weight;
    Boolean evaluationReason;
    Boolean medicationPrescription;
    Boolean patientCondition;
    Boolean scanningTest;
    Boolean scanningTestValue;
    Boolean metalImplantation;
    Boolean pacemaker;
    Boolean surgeriesList;
}

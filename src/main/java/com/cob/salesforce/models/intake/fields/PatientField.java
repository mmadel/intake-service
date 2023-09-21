package com.cob.salesforce.models.intake.fields;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientField {
    private EssentialInformation essentialInformation;
    private AddressInformation addressInformation;
    private MedicalInformation medicalInformation;
    private MedicalHistoryInformation medicalHistoryInformation;
    private InsuranceCompensationInformation insuranceCompensationInformation;
    private InsuranceCommercialInformation insuranceCommercialInformation;
    private Long clinicId;
}

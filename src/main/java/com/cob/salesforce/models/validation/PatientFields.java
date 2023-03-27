package com.cob.salesforce.models.validation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientFields {
    private Long id;
    BasicInfo basicInfo;
    AddressInfoRequired addressInfoRequired;
    MedicalInfoRequired medicalInfoRequired;
}

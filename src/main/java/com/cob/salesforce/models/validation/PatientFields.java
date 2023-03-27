package com.cob.salesforce.models.validation;

import com.cob.salesforce.entity.validation.MedicalHistoryInfoRequiredEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientFields {
    private Long id;
    BasicInfo basicInfo;
    AddressInfoRequired addressInfoRequired;
    MedicalInfoRequired medicalInfoRequired;
    MedicalHistoryInfoRequiredEntity medicalHistoryInfoRequired;
}

package com.cob.salesforce.services.admin.validation;

import com.cob.salesforce.models.validation.BasicInfo;
import com.cob.salesforce.models.validation.PatientFields;

public interface PatientValidationFieldService {
    PatientFields change(PatientFields PatientFields);

    PatientFields get();

    BasicInfo getPateintBasicInfo();
}

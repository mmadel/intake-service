package com.cob.salesforce.services.patient.admin.validation;

import com.cob.salesforce.models.validation.PatientFields;

public interface PatientValidationFieldService {
    public PatientFields change(PatientFields PatientFields);
    public PatientFields get();
}

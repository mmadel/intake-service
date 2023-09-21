package com.cob.salesforce.services.admin.validation;

import com.cob.salesforce.models.intake.fields.PatientField;

public interface PatientValidationFieldService {
    PatientField change(PatientField PatientFields);

    PatientField get(Long clinicId);


}

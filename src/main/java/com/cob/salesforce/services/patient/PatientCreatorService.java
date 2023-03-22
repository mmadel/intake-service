package com.cob.salesforce.services.patient;

import com.cob.salesforce.models.PatientDTO;

public interface PatientCreatorService {
    PatientDTO create(PatientDTO model);
}

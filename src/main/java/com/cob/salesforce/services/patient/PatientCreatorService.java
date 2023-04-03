package com.cob.salesforce.services.patient;

import com.cob.salesforce.models.PatientDTO;

public interface PatientCreatorService {
    Long create(PatientDTO model);
}

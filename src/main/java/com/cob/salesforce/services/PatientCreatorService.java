package com.cob.salesforce.services;

import com.cob.salesforce.models.PatientDTO;

public interface PatientCreatorService {
    Long create(PatientDTO model);
}

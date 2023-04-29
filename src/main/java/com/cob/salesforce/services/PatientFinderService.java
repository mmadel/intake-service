package com.cob.salesforce.services;

import com.cob.salesforce.models.PatientDTO;
import com.cob.salesforce.models.PatientListData;
import org.springframework.data.domain.Pageable;

public interface PatientFinderService {
    PatientListData getPatients(Pageable pageable , Long clinicId);

    PatientDTO getPatient(Long patientId);
}

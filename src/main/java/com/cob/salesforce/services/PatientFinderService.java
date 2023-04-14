package com.cob.salesforce.services;

import com.cob.salesforce.enums.InsuranceWorkerType;
import com.cob.salesforce.enums.PatientSourceType;
import com.cob.salesforce.models.PatientListData;
import com.cob.salesforce.models.pdf.PatientData;
import org.springframework.data.domain.Pageable;

public interface PatientFinderService {
    PatientListData list(Pageable pageable , Long clinicId);

    PatientData getPDFPatientData(InsuranceWorkerType insuranceWorkerType, PatientSourceType patientSourceType, Boolean hasPhysicalTherapy, Long patientId);
}

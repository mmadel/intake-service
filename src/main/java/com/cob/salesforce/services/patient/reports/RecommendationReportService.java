package com.cob.salesforce.services.patient.reports;

import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.models.reporting.PatientSearchResult;

public interface RecommendationReportService {
    PatientSearchResult getReportData(PatientSearchCriteria patientSearchCriteria);
}

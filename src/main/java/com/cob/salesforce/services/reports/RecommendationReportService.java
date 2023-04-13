package com.cob.salesforce.services.reports;

import com.cob.salesforce.models.reporting.PatientSearchCriteria;
import com.cob.salesforce.models.reporting.PatientSearchResult;

public interface RecommendationReportService {
    PatientSearchResult getReportData(PatientSearchCriteria patientSearchCriteria);
}

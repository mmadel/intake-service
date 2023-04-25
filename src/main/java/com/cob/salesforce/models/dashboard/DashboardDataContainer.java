package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Builder
public class DashboardDataContainer {
    private int totalNumberOfPatient;
    private int totalNumberOfCompensationNoFaultPatient;
    private int totalNumberOfCommercialPatient;
    private GenderContainer genderContainer;
    private PatientSourceContainer patientSourceContainer;

    private WeekCounterContainer weekCounterContainer;
    private Map clinicsData;
}

package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DashboardDataContainer {
    private GenderContainer genderContainer;
    private PatientSourceContainer patientSourceContainer;
}

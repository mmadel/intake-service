package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientSourceContainer {
    int doctorPercentage;
    int zocdocPercentage;
    int websitePercentage;
    int socialMediaPercentage;
    int tvPercentage;
}

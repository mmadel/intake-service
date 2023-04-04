package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientSourceContainer {
    int doctorPercentage;
    int doctorNumber;
    int zocdocPercentage;
    int zocdocNumber;
    int websitePercentage;
    int websiteNumber;
    int socialMediaPercentage;
    int socialMediaNumber;
    int tvPercentage;
    int tvNumber;
}

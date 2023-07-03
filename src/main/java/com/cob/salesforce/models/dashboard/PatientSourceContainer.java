package com.cob.salesforce.models.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PatientSourceContainer {
    double doctorPercentage;
    int doctorNumber;
    double zocdocPercentage;
    int zocdocNumber;
    double websitePercentage;
    int websiteNumber;
    double socialMediaPercentage;
    int socialMediaNumber;
    double tvPercentage;
    int tvNumber;
}

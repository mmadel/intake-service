package com.cob.salesforce.models.validation;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsuranceCompInfoRequired {
    private Boolean compNoFault;
    private Boolean relatedInjury;
    private Boolean accidentDate;
    private Boolean workerStatus;
    private Boolean address;
    private Boolean fax;
    private Boolean insuranceName;
    private Boolean claimNumber;
    private Boolean adjusterName;
    private Boolean adjusterPhone;
    private Boolean attorneyName;
    private Boolean attorneyPhone;
    private Boolean caseStatus;
}

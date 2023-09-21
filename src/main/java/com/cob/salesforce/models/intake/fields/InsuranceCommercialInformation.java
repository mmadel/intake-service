package com.cob.salesforce.models.intake.fields;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsuranceCommercialInformation {

    private Boolean insuranceCompany;
    private Boolean memberId;
    private Boolean ploicyId;
    private Boolean relationship;
    private Boolean secondryInsurance;
    private Boolean policyHolderFirstName;
    private Boolean policyHolderMiddleName;
    private Boolean policyHolderLastName;
    private Boolean secondryInsuranceCompany;
    private Boolean secondryInsuranceMemberId;
    private Boolean medicareCoverage;
    private Boolean employerFirstName;
    private Boolean employerMiddleName;
    private Boolean employerLastName;
    private Boolean employerPhone;
}

package com.cob.salesforce.models.pdf;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class InsuranceData {
    private Boolean medicare;
    private Boolean secondaryInsurancePolicy;

    private String insurancePlanName;

    private String policyId;

    private String phone;
    private Boolean secondaryPolicyHolder;




}

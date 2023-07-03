package com.cob.salesforce.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecondaryInsuranceDTO {
    private String policyHolderName;
    private String insuranceCompanyName;

    private String policyHolderFirstName;
    private String policyHolderMeddileName;
    private String policyHolderLastName;

    private String memberId;

    public String getFullName() {
        return policyHolderFirstName + ',' + policyHolderMeddileName
                + ',' + policyHolderLastName;
    }
}

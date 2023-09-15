package com.cob.salesforce.models.intake.insurance.commercial;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SecondaryInsurance  implements Serializable {
    private String policyHolderName;
    private String insuranceCompanyName;

    private String policyHolderFirstName;
    private String policyHolderMiddleName;
    private String policyHolderLastName;

    private String memberId;
}

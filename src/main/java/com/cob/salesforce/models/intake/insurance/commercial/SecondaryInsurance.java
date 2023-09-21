package com.cob.salesforce.models.intake.insurance.commercial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecondaryInsurance  implements Serializable {
    private String policyHolderName;
    private String insuranceCompanyName;

    private String policyHolderFirstName;
    private String policyHolderMiddleName;
    private String policyHolderLastName;

    private String memberId;
}

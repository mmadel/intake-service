
package com.cob.salesforce.models;

import com.cob.salesforce.enums.Relationship;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsuranceWorkerCommercialDTO {

    private Long insuranceCompanyId;
    private Long memberId;

    private Long policyId;

    private String relationship;

    private Boolean isSecondaryInsurance;

    private Boolean isMedicareCoverage;

    private SecondaryInsuranceDTO secondaryInsuranceDTO;

    private MedicareCoverageDTO medicareCoverageDTO;

    private PatientRelationshipDTO patientRelationshipDTO;

    public Relationship getRelationshipEnum() {
        return Relationship.valueOf(relationship);
    }

}

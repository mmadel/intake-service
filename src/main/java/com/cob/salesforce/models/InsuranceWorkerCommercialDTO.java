
package com.cob.salesforce.models;

import com.cob.salesforce.enums.PhoneType;
import com.cob.salesforce.enums.Relationship;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsuranceWorkerCommercialDTO {

    private Long insuranceCompanyId;
    private String memberId;

    private String policyId;

    private String relationship;

    private Boolean isSecondaryInsurance;

    private Boolean isMedicareCoverage;

    private SecondaryInsuranceDTO secondaryInsuranceDTO;

    private MedicareCoverageDTO medicareCoverageDTO;

    private PatientRelationshipDTO patientRelationshipDTO;

    public Relationship getRelationshipEnum() {
        return relationship == "" ? null : Relationship.valueOf(relationship);
    }

}

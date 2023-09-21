package com.cob.salesforce.models.intake.insurance.commercial;

import com.cob.salesforce.models.intake.insurance.commercial.MedicareCoverage;
import com.cob.salesforce.models.intake.insurance.commercial.PatientRelationship;
import com.cob.salesforce.models.intake.insurance.commercial.SecondaryInsurance;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientCommercialInsurance implements Serializable {
    private Long insuranceCompanyId;
    private String memberId;

    private String policyId;

    private String relationship;

    private SecondaryInsurance secondaryInsurance;

    private MedicareCoverage medicareCoverage;

    private PatientRelationship patientRelationship;
}

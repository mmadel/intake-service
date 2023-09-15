package com.cob.salesforce.models.intake.insurance.commercial;

import com.cob.salesforce.models.intake.insurance.commercial.MedicareCoverage;
import com.cob.salesforce.models.intake.insurance.commercial.PatientRelationship;
import com.cob.salesforce.models.intake.insurance.commercial.SecondaryInsurance;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PatientCommercialInsurance implements Serializable {
    private Long insuranceCompanyId;
    private String memberId;

    private String policyId;

    private String relationship;

    private SecondaryInsurance secondaryInsurance;

    private MedicareCoverage medicareCoverage;

    private PatientRelationship patientRelationship;
}

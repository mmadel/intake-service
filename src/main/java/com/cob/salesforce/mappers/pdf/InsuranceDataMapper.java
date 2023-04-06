package com.cob.salesforce.mappers.pdf;

import com.cob.salesforce.entity.InsuranceWorkerCommercial;
import com.cob.salesforce.models.pdf.InsuranceData;

public class InsuranceDataMapper {

    public static InsuranceData map(InsuranceWorkerCommercial source) {
        return InsuranceData.builder()
                .policyId(source.getPolicyId().toString())
                .insurancePlanName(source.getInsuranceCompanyId().toString())
                .secondaryInsurancePolicy(source.getSecondaryInsurance() != null ? true : false)
                .secondaryPolicyHolder(source.getSecondaryInsurance() != null ? true : false)
                .medicare(source.getMedicareCoverage() != null ? true : false)
                .build();
    }
}

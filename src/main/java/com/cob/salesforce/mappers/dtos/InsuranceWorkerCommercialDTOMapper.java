package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.InsuranceWorkerCommercial;
import com.cob.salesforce.models.InsuranceWorkerCommercialDTO;

public class InsuranceWorkerCommercialDTOMapper {

    public static InsuranceWorkerCommercialDTO map(InsuranceWorkerCommercial entity) {
        return InsuranceWorkerCommercialDTO.builder()
                .insuranceCompanyId(entity.getInsuranceCompanyId())
                .memberId(entity.getMemberId())
                .policyId(entity.getPolicyId())
                .relationship(entity.getRelationship().label)
                .isSecondaryInsurance(entity.getSecondaryInsurance() != null ? true : false)
                .isMedicareCoverage(entity.getMedicareCoverage() != null ? true : false)
                .build();
    }
}

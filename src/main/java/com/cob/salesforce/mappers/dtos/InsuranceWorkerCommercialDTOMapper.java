package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.InsuranceWorkerCommercial;
import com.cob.salesforce.models.InsuranceWorkerCommercialDTO;

public class InsuranceWorkerCommercialDTOMapper {

    public static InsuranceWorkerCommercialDTO map(InsuranceWorkerCommercial entity) {
        InsuranceWorkerCommercialDTO dto = new InsuranceWorkerCommercialDTO();
        dto.setInsuranceCompanyId(entity.getInsuranceCompanyId());
        dto.setMemberId(entity.getMemberId());
        dto.setPolicyId(entity.getPolicyId());
        dto.setRelationship(entity.getRelationship().label);
        dto.setIsSecondaryInsurance(entity.getSecondaryInsurance() != null ? true : false);
        dto.setIsMedicareCoverage(entity.getMedicareCoverage() != null ? true : false);
        return dto;
    }
}

package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.models.InsuranceWorkerCompNoFaultDTO;

public class InsuranceWorkerCompNoFaultDTOMapper {

    public static InsuranceWorkerCompNoFaultDTO map(InsuranceWorkerCompNoFault entity) {
        InsuranceWorkerCompNoFaultDTO dto = new InsuranceWorkerCompNoFaultDTO();
        dto.setInjuryType(entity.getInjuryType());
        dto.setAccidentDate(entity.getAccidentDate());
        dto.setWorkerStatus(entity.getWorkerStatus().label);
        dto.setWorkerCompAddress(AddressInfoDTOMapper.map(entity.getAddress()));
        dto.setFax(entity.getFax());
        dto.setInsuranceName(entity.getInsuranceName());
        dto.setClaimNumber(entity.getClaimNumber());
        dto.setAdjusterInfoName(entity.getAdjusterName());
        dto.setAdjusterInfoPhone(entity.getAdjusterPhone());
        dto.setAttorneyInfoName(entity.getAttorneyName());
        dto.setAttorneyInfoPhone(entity.getAttorneyPhone());
        return dto;
    }
}

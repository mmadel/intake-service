package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.InsuranceWorkerCompNoFault;
import com.cob.salesforce.models.InsuranceWorkerCompNoFaultDTO;

public class InsuranceWorkerCompNoFaultDTOMapper {

    public static  InsuranceWorkerCompNoFaultDTO map(InsuranceWorkerCompNoFault entity) {
        return InsuranceWorkerCompNoFaultDTO.builder()
                .injuryType(entity.getInjuryType())
                .accidentDate(entity.getAccidentDate())
                .workerStatus(entity.getWorkerStatus().label)
                .workerCompAddress(AddressInfoDTOMapper.map(entity.getAddress()))
                .fax(entity.getFax())
                .insuranceName(entity.getInsuranceName())
                .claimNumber(entity.getClaimNumber())
                .adjusterInfoName(entity.getAdjusterName())
                .adjusterInfoPhone(entity.getAdjusterPhone())
                .attorneyInfoName(entity.getAttorneyName())
                .attorneyInfoPhone(entity.getAttorneyPhone())
                .build();
    }
}

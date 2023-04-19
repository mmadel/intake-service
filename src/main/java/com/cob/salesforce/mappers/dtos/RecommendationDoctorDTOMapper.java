package com.cob.salesforce.mappers.dtos;

import com.cob.salesforce.entity.PatientDoctorSource;
import com.cob.salesforce.models.RecommendationDoctorDTO;

public class RecommendationDoctorDTOMapper {

    public static RecommendationDoctorDTO map(PatientDoctorSource entity) {
        return RecommendationDoctorDTO.builder()
                .name(entity.getName())
                .npi(entity.getNpi())
                .fax(entity.getFax().toString())
                .doctorAddress(AddressInfoDTOMapper.map(entity.getAddress()))
                .build();
    }
}
